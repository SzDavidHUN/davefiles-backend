package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.entity.FileMeta;
import hu.davidszabo.davefilesbackend.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ImportService importService;

    @GetMapping("/file")
    public FileMeta importFile(
            @RequestParam Path path
    ) throws Exception {
        return importService.importFile(path);
    }

    @GetMapping("/directory")
    public Integer importDirectorySimple(
            @RequestParam Path path
    ) throws IOException {
        return importDirectoryDetailed(path).size();
    }

    @GetMapping("/directory/detailed")
    public Collection<FileMeta> importDirectoryDetailed(
            @RequestParam Path path
    ) throws IOException {
        return importService.importDirectory(path);
    }
}
