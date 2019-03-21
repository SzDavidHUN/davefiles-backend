package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem;

public interface Worker<T> {
    void enable();

    void disable();

    boolean isEnabled();

    boolean isDisabled();

    boolean isRunning();

    void addToQueue(T t);

    void addAllToQueue(Iterable<T> t);

    int getReamining();

    void clear();

    String getName();

    String getDescription();

    WorkerStatusDTO getStatus();
}
