package com.mytodoapp.services;

import com.mytodoapp.data.models.Status;
import com.mytodoapp.data.models.Todo;
import com.mytodoapp.data.models.User;
import com.mytodoapp.data.repositories.TodoRepository;
import com.mytodoapp.data.repositories.UserRepository;
import com.mytodoapp.dtos.reponses.TodoResponse;
import com.mytodoapp.dtos.requests.MarkTaskRequest;
import com.mytodoapp.dtos.requests.TodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoResponse addTask(TodoRequest todoRequest, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Todo todo = new Todo();
        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setStatus(Status.UNCHECKED);
        todo.setUser(user);
        todo.setDateAdded(LocalDateTime.now());
        Todo saved = todoRepository.save(todo);

        TodoResponse response = new TodoResponse();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setDateAdded(saved.getDateAdded());
        response.setStatus(saved.getStatus());
        return response;
    }

    @Override
    public List<TodoResponse> getAllTodoTasks(String userId) {
        List<TodoResponse> responses = new ArrayList<>();
        List<Todo> response = todoRepository.findByUserId(userId);
        for(Todo todo: response){
            TodoResponse todoResponse = new TodoResponse();
            todoResponse.setDescription(todo.getDescription());
            todoResponse.setTitle(todo.getTitle());
            todoResponse.setId(todo.getId());
            todoResponse.setDateAdded(todo.getDateAdded());
            todoResponse.setStatus(todo.getStatus());
            responses.add(todoResponse);
        }
        return responses;
    }

    @Override
    public TodoResponse markAsCompleted(MarkTaskRequest taskRequest, String userId) {
        Todo todo = todoRepository.findById(taskRequest.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if(!todo.getUser().getId().equals(userId)){
            throw new RuntimeException("Task not found");
        }
        todo.setStatus(Status.CHECKED);
        Todo updated = todoRepository.save(todo);
        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setId(updated.getId());
        todoResponse.setTitle(updated.getTitle());
        todoResponse.setDescription(updated.getDescription());
        todoResponse.setDateAdded(updated.getDateAdded());
        todoResponse.setStatus(updated.getStatus());
        return todoResponse;
    }
}
