package hu.davidszabo.davefilesbackend.service;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.exception.FileCouldntBeImportedException;
import hu.davidszabo.davefilesbackend.repository.FileMetaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImportService {

    @Autowired
    private FileMetaRepository fileMetaRepository;
    private Logger logger = LoggerFactory.getLogger(ImportService.class);

    public FileMeta importFile(Path path) throws Exception {
        if (!Files.exists(path))
            throw new FileCouldntBeImportedException(1, "File couldn't be found", path);
        FileMeta fileMeta = new FileMeta(path.toFile().getName(), path);
        fileMeta = fileMetaRepository.save(fileMeta);
        return fileMeta;
    }

    public Collection<FileMeta> importDirectory(Path path) throws IOException {
        Collection<FileMeta> fileToSave = new LinkedList<>();
        logger.info("Walking dir: " + path.toString());
        Collection<Path> paths = walkDirectory(path);
        logger.info("Creating filemetas..");
        for (Path i : paths)
            if (fileMetaRepository.findByPath(i).isEmpty())
                fileToSave.add(new FileMeta(i.toFile().getName(), i));
        logger.info("Saving to DB..");
        List<FileMeta> fileMetas = fileMetaRepository.saveAll(fileToSave);
        logger.info("Done!");
        return fileMetas;
    }

    private Collection<Path> walkDirectory(Path path) throws IOException {
        ArrayList<Path> files = new ArrayList<>();

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                files.add(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                System.out.println("[Warning][FileDiscoveryService]: Couldn't visit path: " + path.toString()); //TODO log
                return FileVisitResult.SKIP_SUBTREE;
            }
        });

        return files;
    }

}
