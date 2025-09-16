package com.mytodoapp.data.repositories;

import com.mytodoapp.data.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {
    List<Todo> findByUserId(String userId);
}
