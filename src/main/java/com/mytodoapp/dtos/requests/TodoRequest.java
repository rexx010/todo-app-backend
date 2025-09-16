package com.mytodoapp.dtos.requests;

import com.mytodoapp.data.models.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    @NotBlank(message = "Task title is required")
    private String title;
    @NotBlank(message = "Task description is required")
    private String description;
}
