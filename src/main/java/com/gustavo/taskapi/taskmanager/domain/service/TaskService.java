package com.gustavo.taskapi.taskmanager.domain.service;

import com.gustavo.taskapi.taskmanager.domain.entity.Task;
import com.gustavo.taskapi.taskmanager.domain.entity.User;
import com.gustavo.taskapi.taskmanager.domain.repository.TaskRepository;
import com.gustavo.taskapi.taskmanager.domain.repository.UserRepository;
import com.gustavo.taskapi.taskmanager.dto.CreateTaskRequest;
import com.gustavo.taskapi.taskmanager.dto.TaskMapper;
import com.gustavo.taskapi.taskmanager.dto.TaskResponse;
import com.gustavo.taskapi.taskmanager.dto.UpdateTaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponse createTask(CreateTaskRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .user(user)
                .createdAt(java.time.LocalDateTime.now())
                .updatedAt(java.time.LocalDateTime.now())
                .dueDate(java.time.LocalDateTime.now().plusDays(7))
                .build();

        taskRepository.save(task);
        return TaskMapper.toResponse(task);
    }


    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        // O campo updatedAt será atualizado automaticamente pelo método @PreUpdate

        taskRepository.save(task);
        return TaskMapper.toResponse(task);
    }


    public List<TaskResponse> listAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        taskRepository.deleteById(id);
    }
}
