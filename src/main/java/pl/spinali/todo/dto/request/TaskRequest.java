package pl.spinali.todo.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueAt;
    private boolean completed;
}
