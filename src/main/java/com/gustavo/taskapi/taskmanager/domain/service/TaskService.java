package com.gustavo.taskapi.taskmanager.domain.service;

import com.gustavo.taskapi.taskmanager.domain.entity.Task;
import com.gustavo.taskapi.taskmanager.domain.entity.TaskStatus;
import com.gustavo.taskapi.taskmanager.domain.repository.TaskRepository;
import com.gustavo.taskapi.taskmanager.dto.CreateTaskRequest;
import com.gustavo.taskapi.taskmanager.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    
    public TaskDTO createTask(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setStatus(TaskStatus.TODO);
        task.setUser(userService.getByIdEntity(request.userId()));

        return toDTO(taskRepository.save(task));
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return toDTO(findTaskByIdOrThrow(id));
    }

    public TaskDTO updateTask(Long id, CreateTaskRequest request) {
        Task task = findTaskByIdOrThrow(id);
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setUser(userService.getByIdEntity(request.userId()));

        return toDTO(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada para exclusão com id: " + id);
        }
        taskRepository.deleteById(id);
    }

    public TaskDTO updateTaskStatus(Long id, TaskStatus newStatus) {
        Task task = findTaskByIdOrThrow(id);
        task.setStatus(newStatus);
        return toDTO(taskRepository.save(task));
    }

    private TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getUser().getId()
        );
    }

    private Task findTaskByIdOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id: " + id));
    }
}