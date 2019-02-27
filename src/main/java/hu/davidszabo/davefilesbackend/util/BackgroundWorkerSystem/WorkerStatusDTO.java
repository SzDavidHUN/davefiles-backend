package hu.davidszabo.davefilesbackend.util.BackgroundWorkerSystem;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.NONE)
@AllArgsConstructor
public class WorkerStatusDTO {
    private String name;
    private String description;
    private int reamining;
    private boolean isEnabled;
    private boolean isRunning;
}
