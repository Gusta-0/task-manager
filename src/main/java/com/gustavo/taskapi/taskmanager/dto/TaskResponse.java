package com.gustavo.taskapi.taskmanager.dto;

import com.gustavo.taskapi.taskmanager.domain.entity.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime dueDate,
        Long userId
) {}