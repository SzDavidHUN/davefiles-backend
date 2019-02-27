package hu.davidszabo.davefilesbackend.service;

import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.Worker;
import hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem.WorkerStatusDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class WorkerService {

    private List<Worker> workers;

    @PostConstruct
    private void init() {
        workers = new LinkedList<>();
    }

    public void registerWorker(Worker worker) {
        workers.add(worker);
    }

    public Collection<WorkerStatusDTO> listAllWorkers() {
        Collection<WorkerStatusDTO> workerStatusDTOS = new LinkedList<>();
        workers.forEach(worker -> workerStatusDTOS.add(worker.getStatus()));
        return workerStatusDTOS;
    }

    public WorkerStatusDTO getWorkerStatus(Integer id) {
        return workers.get(id).getStatus();
    }

    public WorkerStatusDTO enableWorker(Integer id) {
        Worker worker = workers.get(id);
        worker.enable();
        return worker.getStatus();
    }

    public WorkerStatusDTO disableWorker(Integer id) {
        Worker worker = workers.get(id);
        worker.disable();
        return worker.getStatus();
    }
}
