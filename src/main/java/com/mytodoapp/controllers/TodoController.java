package com.mytodoapp.controllers;

import com.mytodoapp.dtos.reponses.TodoResponse;
import com.mytodoapp.dtos.requests.DelRequest;
import com.mytodoapp.dtos.requests.MarkTaskRequest;
import com.mytodoapp.dtos.requests.TodoRequest;
import com.mytodoapp.dtos.requests.UpdateRequest;
import com.mytodoapp.services.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<?> addTask(@Valid @RequestBody TodoRequest todoRequest, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.addTask(todoRequest, userId));
    }

    @GetMapping("/gettask")
    public ResponseEntity<?> getAllTodoTasks(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        System.out.println("Id" +userId);
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.getAllTodoTasks(userId));
    }

    @GetMapping("/getincompleted")
    public ResponseEntity<?> getAllInCompletedTask(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.getAllInCompletedTask(userId));
    }

    @GetMapping("/getcompleted")
    public ResponseEntity<?> getAllCompletedTask(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.getAllCompletedTask(userId));
    }

    @GetMapping("/mark/{taskId}")
    public ResponseEntity<?> markAsCompleted(@PathVariable("taskId") String taskId, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.markAsCompleted(taskId, userId));
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("taskId") String taskId, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if (userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        todoService.deleteTask(taskId, userId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable ("taskId")String taskId, @Valid @RequestBody UpdateRequest request, HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if (userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        return ResponseEntity.ok(todoService.updateTask(taskId, request, userId));
    }
}
