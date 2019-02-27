package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.MimeType;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.ElementProcessor;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MimeTypeProcessor<T extends FileMeta> implements ElementProcessor<T> {

    private Tika tika;
    private Logger logger;

    public MimeTypeProcessor() {
        logger = LoggerFactory.getLogger(MimeTypeProcessor.class);
        tika = new Tika();
    }

    public T process(T fileMeta) {
        if (fileMeta.getMainType() != null && !fileMeta.getMainType().isBlank())
            logger.info("MainType isn't empty [UUID: " + fileMeta.getUuid() + "] [Path: " + fileMeta.getPath() + "]");
        try {
            String mimetype = tika.detect(fileMeta.getPath());
            if (mimetype == null || mimetype.isBlank()) {
                logger.warn("Mimetype is null! [UUID: " + fileMeta.getUuid() + "][Path: " + fileMeta.getPath() + "]");
                return fileMeta;
            }
            String[] split = mimetype.split("/", 2);
            fileMeta.setMainType(split[0]);
            fileMeta.setSubType(split[1]);
        } catch (IOException e) {
            logger.warn("IOException: " + e.getMessage());
            logger.debug(e.getStackTrace().toString());
            e.printStackTrace();
            return fileMeta;
        }

        return fileMeta;
    }
}
