package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.exception.UUIDCouldntBeFoundException;
import hu.davidszabo.davefilesbackend.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/list")
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping({"/all", "/", ""})
    public Collection<FileMeta> listAllFiles() {
        return listService.listAllFiles();
    }

    @GetMapping("/uuid/{uuid}")
    public FileMeta getFileByUUID(
            @PathVariable UUID uuid
    ) throws UUIDCouldntBeFoundException {
        return listService.getFileByUUID(uuid);
    }

    @GetMapping("/filename/{filename}")
    public Collection<FileMeta> listFilesByFilename(
            @PathVariable String filename
    ) {
        return listService.getFileByFilename(filename);
    }
}
