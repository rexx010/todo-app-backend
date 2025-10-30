package com.mytodoapp.services;

import com.mongodb.internal.bulk.DeleteRequest;
import com.mytodoapp.data.models.Todo;
import com.mytodoapp.dtos.reponses.TodoResponse;
import com.mytodoapp.dtos.requests.DelRequest;
import com.mytodoapp.dtos.requests.MarkTaskRequest;
import com.mytodoapp.dtos.requests.TodoRequest;
import com.mytodoapp.dtos.requests.UpdateRequest;

import java.util.List;

public interface TodoService {
    TodoResponse addTask(TodoRequest todoRequest, String userId);
    List<Todo> getAllTodoTasks(String userId);
    List<Todo> getAllInCompletedTask(String userId);
    List<Todo> getAllCompletedTask(String userId);
    TodoResponse markAsCompleted(String taskId, String userId);
    void deleteTask(String taskId, String userId);
    TodoResponse updateTask(String taskId, UpdateRequest request, String userId);
}
