package pl.spinali.todo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskStatusRequest {
    @NotNull
    private Boolean completed;
}
