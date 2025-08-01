package com.gustavo.taskapi.taskmanager.dto;

import com.gustavo.taskapi.taskmanager.domain.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskRequest(
        @NotBlank String title,
        String description,
        @NotNull(message = "Status não pode ser nulo. Valores válidos: TODO, DOING ou DONE") TaskStatus status
) {}