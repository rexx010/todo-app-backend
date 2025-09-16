package com.mytodoapp.services;

import com.mytodoapp.dtos.reponses.TodoResponse;
import com.mytodoapp.dtos.requests.MarkTaskRequest;
import com.mytodoapp.dtos.requests.TodoRequest;

import java.util.List;

public interface TodoService {
    TodoResponse addTask(TodoRequest todoRequest, String userId);
    List<TodoResponse> getAllTodoTasks(String userId);
    TodoResponse markAsCompleted(MarkTaskRequest taskRequest, String userId);
}
