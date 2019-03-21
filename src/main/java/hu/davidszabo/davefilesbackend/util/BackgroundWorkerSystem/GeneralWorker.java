package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem;

import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public abstract class GeneralWorker<Type, Processor extends ElementProcessor<Type>> implements Worker<Type> {
    private List<Processor> processors;
    private Queue<Type> inputQueue;
    private boolean enabled;
    private boolean running;
    private Logger logger;

    @PostConstruct
    private void init() {
        init(1);
    }

    private void init(Integer parallelThreads) {
        logger = initLogger();
        inputQueue = new LinkedTransferQueue<>();
        enabled = false;
        running = false;
        processors = new ArrayList<>(parallelThreads);
        for (int i = 0; i < parallelThreads; i++) {
            processors.add(initProcessor(i));
        }
    }

    protected abstract Logger initLogger();

    protected abstract Processor initProcessor(int numberOfThread);

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
            Type processed = processors.get(0).process(inputQueue.poll());
            save(processed);
        }
        logger.info("Stopped.");
        running = false;
    }

    protected abstract Type save(Type object);

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
    public void addToQueue(Type object) {
        inputQueue.add(object);
        if (enabled && !running) {
            logger.info("Started by adding item");
            start();
        }
    }

    @Override
    public void addAllToQueue(Iterable<Type> t) {
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
    public void clear() {
        inputQueue.clear();
    }

    @Override
    public abstract String getName();

    @Override
    public abstract String getDescription();

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
