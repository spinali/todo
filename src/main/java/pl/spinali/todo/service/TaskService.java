package pl.spinali.todo.service;

import org.springframework.stereotype.Service;
import pl.spinali.todo.dto.request.TaskRequest;
import pl.spinali.todo.dto.response.TaskResponse;
import pl.spinali.todo.model.Task;
import pl.spinali.todo.repository.TaskRepository;
import pl.spinali.todo.support.mapper.TaskMapper;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = new TaskMapper();
    }
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        return taskMapper.toTaskResponse(task);
    }
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskRepository.save(taskMapper.toTask(taskRequest));
        return taskMapper.toTaskResponse(task);
    }
}
