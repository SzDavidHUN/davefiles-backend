package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.exception.UUIDCouldntBeFoundException;
import hu.davidszabo.davefilesbackend.service.ContentAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cac")
public class ContentAnalitycsController {
    @Autowired
    private ContentAnalyticsService contentAnalyticsService;

    @GetMapping("/empty")
    public void analyzeAllEmpty() {
        contentAnalyticsService.analyzeAllEmpty();
    }

    @GetMapping("/{uuid}")
    public void analyzeUuid(
            @PathVariable UUID uuid
    ) throws UUIDCouldntBeFoundException {
        contentAnalyticsService.analyzeUUID(uuid);
    }
}
