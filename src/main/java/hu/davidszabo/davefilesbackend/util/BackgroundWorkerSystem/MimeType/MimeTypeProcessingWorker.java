package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.MimeType;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.repository.FileMetaRepository;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.Worker;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.WorkerStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

@Component
public class MimeTypeProcessingWorker implements Worker<FileMeta> {

    private MimeTypeProcessor[] mimeTypeProcessors;
    private Queue<FileMeta> inputQueue;
    private boolean enabled;
    private boolean running;
    private Logger logger;
    @Autowired
    private FileMetaRepository fileMetaRepository;

    @PostConstruct
    private void init() {
        init(1);
    }

    private void init(Integer parallelThreads) {
        logger = LoggerFactory.getLogger(MimeTypeProcessingWorker.class);
        inputQueue = new LinkedTransferQueue<>();
        enabled = false;
        running = false;
        mimeTypeProcessors = new MimeTypeProcessor[parallelThreads];
        for (int i = 0; i < parallelThreads; i++) {
            mimeTypeProcessors[i] = new MimeTypeProcessor();
        }
    }

    @Override
    public void enable() {
        if (enabled) {
            if (running)
                logger.info("Already enabled, running");
            else
                logger.info("Already enabled, not running");
            return;
        }
        enabled = true;
        logger.info("Started by enabling");
        start();
    }

    private void start() {
        if (running) {
            logger.error("Already running!");
            return;
        }
        if (!enabled) {
            logger.error("Not enabled!");
            return;
        }
        running = true;
        while (running) {
            if (inputQueue.isEmpty()) {
                running = false;
                logger.info("Stopped, no items in inputQueue");
                return;
            }
            FileMeta processed = mimeTypeProcessors[0].process(inputQueue.poll());
            fileMetaRepository.save(processed);
        }
        logger.info("Stopped.");
        running = false;
    }

    private void stop() {
        running = false;
    }

    @Override
    public void disable() {
        enabled = false;
        logger.info("Stopping by disabling..");
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isDisabled() {
        return enabled;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void addToQueue(FileMeta fileMeta) {
        inputQueue.add(fileMeta);
        if (enabled && !running) {
            logger.info("Started by adding item");
            start();
        }
    }

    @Override
    public void addAllToQueue(Iterable<FileMeta> t) {
        t.forEach(i -> inputQueue.add(i));
        if (enabled && !running) {
            logger.info("Started by adding items");
            start();
        }
    }

    @Override
    public int getReamining() {
        return inputQueue.size();
    }

    @Override
    public String getName() {
        return "MimeTypeProcessingService";
    }

    @Override
    public String getDescription() {
        return "asd";
    }

    @Override
    public WorkerStatusDTO getStatus() {
        return new WorkerStatusDTO(
                getName(),
                getDescription(),
                getReamining(),
                isEnabled(),
                isRunning()
        );
    }
}
