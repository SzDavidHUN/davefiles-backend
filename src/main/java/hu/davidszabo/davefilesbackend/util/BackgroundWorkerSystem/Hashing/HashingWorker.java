package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.Hashing;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.repository.FileMetaRepository;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.GeneralWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HashingWorker extends GeneralWorker<FileMeta, Hasher> {
    @Autowired
    private FileMetaRepository fileMetaRepository;

    @Override
    protected Logger initLogger() {
        return LoggerFactory.getLogger(HashingWorker.class);
    }

    @Override
    protected Hasher initProcessor(int numberOfThread) {
        return new Hasher();
    }

    @Override
    protected FileMeta save(FileMeta object) {
        return fileMetaRepository.save(object);
    }

    @Override
    public String getName() {
        return "HashingWorker";
    }

    @Override
    public String getDescription() {
        return "Worker responsible for hashing stuff";
    }
}
