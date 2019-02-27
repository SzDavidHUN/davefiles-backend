package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem;

public interface ElementProcessor<T> {
    T process(T t);
}
