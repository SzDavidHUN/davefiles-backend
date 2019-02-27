package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.service.ContentAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cac")
public class ContentAnalitycsController {
    @Autowired
    private ContentAnalyticsService contentAnalyticsService;

    @GetMapping("/empty")
    public void analyzeAllEmpty() {
        contentAnalyticsService.analyzeAllEmpty();
    }
}
