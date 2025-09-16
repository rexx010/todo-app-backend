package com.mytodoapp.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MarkTaskRequest {
    @NotBlank(message = "Task id is required")
    private String taskId;
}
