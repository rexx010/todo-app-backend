package com.mytodoapp.dtos.requests;

import com.mytodoapp.data.models.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoRequest {
    private String title;
    private String description;
}
