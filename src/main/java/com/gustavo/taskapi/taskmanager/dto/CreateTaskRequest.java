package com.gustavo.taskapi.taskmanager.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTaskRequest(
        @NotBlank(message = "Título é obrigatório")
        String title,

        String description,

        @NotNull(message = "Data de vencimento é obrigatória")
        @Future(message = "A data de vencimento deve ser futura")
        LocalDateTime dueDate,

        @NotNull(message = "ID do usuário é obrigatório")
        Long userId
) {}