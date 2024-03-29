package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.MimeType;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.repository.FileMetaRepository;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.GeneralWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MimeTypeProcessingWorker extends GeneralWorker<FileMeta, MimeTypeProcessor<FileMeta>> {

    @Autowired
    private FileMetaRepository fileMetaRepository;

    @Override
    protected Logger initLogger() {
        return LoggerFactory.getLogger(MimeTypeProcessingWorker.class);
    }

    @Override
    protected MimeTypeProcessor initProcessor(int numberOfThread) {
        return new MimeTypeProcessor();
    }

    @Override
    protected FileMeta save(FileMeta object) {
        return fileMetaRepository.save(object);
    }

    @Override
    public String getName() {
        return "MimeTypeProcessingService";
    }

    @Override
    public String getDescription() {
        return "asd";
    }
}
