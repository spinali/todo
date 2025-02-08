package pl.spinali.todo.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import pl.spinali.todo.model.Task;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueAt;
    private boolean completed;
    public TaskResponse(Long id, @NonNull String title, String description, @NonNull LocalDateTime createdAt, LocalDateTime updatedAt, @NonNull LocalDateTime dueAt, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dueAt = dueAt;
        this.completed = completed;
    }
}
