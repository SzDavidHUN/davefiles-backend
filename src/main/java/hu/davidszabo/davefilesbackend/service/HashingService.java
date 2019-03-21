package hu.davidszabo.davefilesbackend.service;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.exception.UUIDCouldntBeFoundException;
import hu.davidszabo.davefilesbackend.repository.FileMetaRepository;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.Hashing.HashingWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class HashingService {
    @Autowired
    private FileMetaRepository fileMetaRepository;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private HashingWorker hashingWorker;

    @PostConstruct
    void init() {
        workerService.registerWorker(hashingWorker);
    }

    public int analyzeanalyzeAllEmpty() {
        Collection<FileMeta> allBySha256IsNull = fileMetaRepository.findAllBySha256IsNull();
        hashingWorker.addAllToQueue(allBySha256IsNull);
        return allBySha256IsNull.size();
    }

    public void analyzeUUID(UUID uuid) throws UUIDCouldntBeFoundException {
        Optional<FileMeta> optionalFileMeta = fileMetaRepository.findById(uuid);
        if (optionalFileMeta.isEmpty())
            throw new UUIDCouldntBeFoundException(100, "UUID Couldn't be found!", uuid);
        hashingWorker.addToQueue(optionalFileMeta.get());
    }
}
