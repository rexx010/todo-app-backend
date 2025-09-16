package com.mytodoapp.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class Todo {
    @Id
    private String id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime dateAdded;
    @DBRef
    private User user;
}
