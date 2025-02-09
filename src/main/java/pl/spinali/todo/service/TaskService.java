package pl.spinali.todo.service;

import org.springframework.stereotype.Service;
import pl.spinali.todo.dto.request.TaskRequest;
import pl.spinali.todo.dto.request.TaskStatusRequest;
import pl.spinali.todo.dto.response.TaskResponse;
import pl.spinali.todo.model.Task;
import pl.spinali.todo.repository.TaskRepository;
import pl.spinali.todo.support.mapper.TaskMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        return taskMapper.toTaskResponse(task);
    }
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(taskMapper::toTaskResponse).collect(Collectors.toList());
    }
    public TaskResponse createTask(TaskRequest taskRequest) {
        taskRequest.setCreatedAt(LocalDateTime.now());
        taskRequest.setCompleted(false);
        Task task = taskRepository.save(taskMapper.toTask(taskRequest));
        return taskMapper.toTaskResponse(task);
    }
    public TaskResponse updateTask(TaskRequest taskRequest, Long id){
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        task.setCompleted(taskRequest.isCompleted());
        taskRepository.save(taskMapper.toTask(task, taskRequest));
        return taskMapper.toTaskResponse(task);
    }

    public TaskResponse deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        taskRepository.delete(task);
        return taskMapper.toTaskResponse(task);
    }

    public TaskResponse updateCompletionStatus(Long id, boolean completed) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        task.setCompleted(completed);
        Task updatedTask = taskRepository.save(task);
        return taskMapper.toTaskResponse(updatedTask);
    }
}
