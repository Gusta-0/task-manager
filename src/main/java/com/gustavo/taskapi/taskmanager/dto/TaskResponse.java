package com.gustavo.taskapi.taskmanager.dto;

import com.gustavo.taskapi.taskmanager.domain.entity.TaskStatus;

import java.time.LocalDateTime;

public record TaskDTO(
        Long id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        Long userId
) {}
