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

    public TaskRequest(String title, String description, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dueAt, boolean completed) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dueAt = dueAt;
        this.completed = completed;
    }
}
