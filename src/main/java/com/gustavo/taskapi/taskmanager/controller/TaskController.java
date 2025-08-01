package com.gustavo.taskapi.taskmanager.controller;

import com.gustavo.taskapi.taskmanager.domain.entity.TaskStatus;
import com.gustavo.taskapi.taskmanager.dto.CreateTaskRequest;
import com.gustavo.taskapi.taskmanager.dto.TaskResponse;
import com.gustavo.taskapi.taskmanager.domain.service.TaskService;
import com.gustavo.taskapi.taskmanager.dto.UpdateTaskRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id,
                                               @Valid @RequestBody UpdateTaskRequest request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> listAll() {
        return ResponseEntity.ok(taskService.listAllTasks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
