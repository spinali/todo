package pl.spinali.todo.support.mapper;

import org.springframework.stereotype.Component;
import pl.spinali.todo.dto.request.TaskRequest;
import pl.spinali.todo.dto.response.TaskResponse;
import pl.spinali.todo.model.Task;

@Component
public class TaskMapper {


    public Task toTask(TaskRequest taskRequest) {
        return new Task(taskRequest.getTitle(), taskRequest.getDescription(), taskRequest.getCreatedAt(), taskRequest.getUpdatedAt(), taskRequest.getDueAt(), taskRequest.isCompleted());
    }

    public TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getDueAt(),
                task.isCompleted()
        );
    }
}
