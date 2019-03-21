package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.exception.UUIDCouldntBeFoundException;
import hu.davidszabo.davefilesbackend.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/paged")
    public Page<FileMeta> listAllFilesPaged(
            @RequestParam Integer pagesize,
            @RequestParam Integer page
    ) {
        return listService.listAllFilesPaged(pagesize, page);
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
