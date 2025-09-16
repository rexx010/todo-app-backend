package com.mytodoapp.controllers;

import com.mytodoapp.dtos.requests.MarkTaskRequest;
import com.mytodoapp.dtos.requests.TodoRequest;
import com.mytodoapp.services.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody TodoRequest todoRequest, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.addTask(todoRequest, userId));
    }

    @GetMapping("/gettask")
    public ResponseEntity<?> getAllTodoTasks(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.getAllTodoTasks(userId));
    }

    @PostMapping("/mark")
    public ResponseEntity<?> markAsCompleted(@RequestBody MarkTaskRequest taskRequest, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.markAsCompleted(taskRequest, userId));
    }
}
