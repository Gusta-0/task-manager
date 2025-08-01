package com.gustavo.taskapi.taskmanager.dto;

import com.gustavo.taskapi.taskmanager.domain.entity.Task;

public class TaskMapper {

    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getDueDate(),
                task.getUser().getId()
        );
    }
}
