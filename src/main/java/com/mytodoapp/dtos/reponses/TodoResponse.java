package com.mytodoapp.dtos.reponses;

import com.mytodoapp.data.models.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoResponse {
    private String id;
    private String title;
    private String description;
    private LocalDateTime dateAdded;
    private Status status;
}
