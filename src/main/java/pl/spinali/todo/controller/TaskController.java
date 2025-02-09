package pl.spinali.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.spinali.todo.dto.request.TaskRequest;
import pl.spinali.todo.dto.request.TaskStatusRequest;
import pl.spinali.todo.dto.response.TaskResponse;
import pl.spinali.todo.service.TaskService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/{id}")
    @Operation(summary="find task by id")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id){
        TaskResponse taskResponse = taskService.getTaskById(id);
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }
    @GetMapping
    @Operation(summary = "get all tasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        List<TaskResponse> taskResponses = new ArrayList<>(taskService.getAllTasks());
        return new ResponseEntity<>(taskResponses, HttpStatus.OK);
    }
    @PostMapping()
    @Operation(summary = "create new task")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest){
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        System.out.println("Otrzymano dueAt: " + taskResponse.getDueAt());
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @Operation(summary="edit task by id")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.updateTask(taskRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(taskResponse);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete task by id")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("/{id}")
    @Operation(summary = "set task completion status")
    public ResponseEntity<TaskResponse> updateTaskCompletionStatus(
            @PathVariable Long id,
            @Valid @RequestBody TaskStatusRequest taskStatusRequest) {
        TaskResponse taskResponse = taskService.updateCompletionStatus(id, taskStatusRequest.getCompleted());
        return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }
}
