package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.exception.UUIDCouldntBeFoundException;
import hu.davidszabo.davefilesbackend.service.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/hash/")
public class HashingController {
    @Autowired
    private HashingService hashingService;

    @GetMapping("/empty")
    public int analyzeAllEmpty() {
        return hashingService.analyzeanalyzeAllEmpty();
    }

    @GetMapping("/uuid/{uuid}")
    public void analyzeUUID(
            @PathVariable UUID uuid
    ) throws UUIDCouldntBeFoundException {
        hashingService.analyzeUUID(uuid);
    }
}
