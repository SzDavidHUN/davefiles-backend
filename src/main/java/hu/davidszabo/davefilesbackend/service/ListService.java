package hu.davidszabo.davefilesbackend.service;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.exception.UUIDCouldntBeFoundException;
import hu.davidszabo.davefilesbackend.repository.FileMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService {

    @Autowired
    private FileMetaRepository fileMetaRepository;

    public Collection<FileMeta> listAllFiles() {
        return fileMetaRepository.findAll();
    }

    public FileMeta getFileByUUID(UUID uuid) throws UUIDCouldntBeFoundException {
        Optional<FileMeta> result = fileMetaRepository.findById(uuid);
        if (result.isEmpty())
            throw new UUIDCouldntBeFoundException(1, "Requested UUID couldn't be found", uuid);
        return result.get();
    }

    public Collection<FileMeta> getFileByFilename(String filename) {
        return fileMetaRepository.findAllByPathEndingWith(filename);
    }

    public Page<FileMeta> listAllFilesPaged(Integer pageSize, Integer page) {
        return fileMetaRepository.findAll(PageRequest.of(page, pageSize));
    }
}
