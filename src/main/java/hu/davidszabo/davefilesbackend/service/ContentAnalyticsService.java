package hu.davidszabo.davefilesbackend.service;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.exception.UUIDCouldntBeFoundException;
import hu.davidszabo.davefilesbackend.repository.FileMetaRepository;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.MimeType.MimeTypeProcessingWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContentAnalyticsService {
    @Autowired
    private FileMetaRepository fileMetaRepository;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private MimeTypeProcessingWorker mimeTypeProcessingWorker;

    @PostConstruct
    private void init() {
        //mimeTypeProcessingWorker = new MimeTypeProcessingWorker(1);
        workerService.registerWorker(mimeTypeProcessingWorker);
    }

    public void analyzeAllEmpty() {
        Collection<FileMeta> allByMainTypeIsNull = fileMetaRepository.findAllByMainTypeIsNull();
        mimeTypeProcessingWorker.addAllToQueue(allByMainTypeIsNull);
    }

    public void analyzeUUID(UUID uuid) throws UUIDCouldntBeFoundException {
        Optional<FileMeta> optionalFileMeta = fileMetaRepository.findById(uuid);
        if (optionalFileMeta.isEmpty())
            throw new UUIDCouldntBeFoundException(100, "UUID Couldn't be found!", uuid);
        mimeTypeProcessingWorker.addToQueue(optionalFileMeta.get());
    }
}
