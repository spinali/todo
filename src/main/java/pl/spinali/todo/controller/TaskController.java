package pl.spinali.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.spinali.todo.dto.request.TaskRequest;
import pl.spinali.todo.dto.response.TaskResponse;
import pl.spinali.todo.service.TaskService;

@Controller
@RequestMapping("/api")
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
    @PostMapping("/add")
    @Operation(summary = "create new task")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest){
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        return new ResponseEntity<>(taskResponse, HttpStatus.CREATED);
    }
}
