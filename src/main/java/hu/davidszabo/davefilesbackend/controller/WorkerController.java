package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.service.WorkerService;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.WorkerStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @GetMapping({"/all", "/", ""})
    private Collection<WorkerStatusDTO> listAllWorkers() {
        return workerService.listAllWorkers();
    }

    @GetMapping("/{id}")
    private WorkerStatusDTO showWorker(
            @PathVariable Integer id
    ) {
        return workerService.getWorkerStatus(id);
    }

    @GetMapping("/{id}/enable")
    private WorkerStatusDTO enableWorker(
            @PathVariable Integer id
    ) {
        return workerService.enableWorker(id);
    }

    @GetMapping("/{id}/disable")
    private WorkerStatusDTO disableWorker(
            @PathVariable Integer id
    ) {
        return workerService.disableWorker(id);
    }
}
