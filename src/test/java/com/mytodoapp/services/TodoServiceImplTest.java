package com.mytodoapp.services;

import com.mytodoapp.data.models.Status;
import com.mytodoapp.data.models.Todo;
import com.mytodoapp.data.repositories.TodoRepository;
import com.mytodoapp.data.repositories.UserRepository;
import com.mytodoapp.dtos.reponses.TodoResponse;
import com.mytodoapp.dtos.reponses.UserResponse;
import com.mytodoapp.dtos.requests.MarkTaskRequest;
import com.mytodoapp.dtos.requests.TodoRequest;
import com.mytodoapp.dtos.requests.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoServiceImplTest {
    @Autowired
    private TodoServiceImpl todoService;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testThatTodoTaskCanAdd(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Rex01");
        userRequest.setEmail("grex200@gmail.com");
        userRequest.setPassword("12345");

        UserResponse response = userService.registeredUser(userRequest);

        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setTitle("Watch Movies");
        todoRequest.setDescription("Watching movies is fun");
        todoService.addTask(todoRequest, response.getId());
        assertEquals(1, todoRepository.count());
    }

    @Test
    public void testThatMoreThanOneTaskCanBeAdded_UserCanGetAll(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("R1");
        userRequest.setEmail("r@gmail.com");
        userRequest.setPassword("12345");

        UserResponse response = userService.registeredUser(userRequest);

        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setTitle("Watch Movies");
        todoRequest.setDescription("Watching movies is fun");
        todoService.addTask(todoRequest, response.getId());
        assertEquals(1, todoRepository.count());

        UserRequest userRequest2 = new UserRequest();
        userRequest2.setUsername("x1");
        userRequest2.setEmail("x20@gmail.com");
        userRequest2.setPassword("12345");

        UserResponse response2 = userService.registeredUser(userRequest2);

        TodoRequest todoRequest2 = new TodoRequest();
        todoRequest2.setTitle("Watch Movies");
        todoRequest2.setDescription("Watching movies is fun");
        todoService.addTask(todoRequest, response2.getId());
        assertEquals(2, todoRepository.count());
    }

    @Test
    public void testThatMoreThanOneTaskCanBeAdded_UserCanGetList(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("femii");
        userRequest.setEmail("fiim@gmail.com");
        userRequest.setPassword("12345");

        UserResponse response = userService.registeredUser(userRequest);

        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setTitle("Watch Movies");
        todoRequest.setDescription("Watching movies is fun");
        todoService.addTask(todoRequest, response.getId());
        assertEquals(1, todoRepository.count());

        TodoRequest todoRequest2 = new TodoRequest();
        todoRequest2.setTitle("Watch football");
        todoRequest2.setDescription("Watching football is fun");
        todoService.addTask(todoRequest2, response.getId());
        assertEquals(2, todoRepository.count());

        List<Todo> res = todoService.getAllTodoTasks(response.getId());
        assertEquals(2, res.size());
    }

    @Test
    public void testThatMoreThanOneTaskCanBeAdded_UserCanMarkTaskAsDone(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("segzz");
        userRequest.setEmail("segzz@gmail.com");
        userRequest.setPassword("12345");

        UserResponse response = userService.registeredUser(userRequest);

        TodoRequest todoRequest = new TodoRequest();
        todoRequest.setTitle("Watch Movies");
        todoRequest.setDescription("Watching movies is fun");
        todoService.addTask(todoRequest, response.getId());
        assertEquals(1, todoRepository.count());

        TodoRequest todoRequest2 = new TodoRequest();
        todoRequest2.setTitle("Watch ball");
        todoRequest2.setDescription("Watching football is fun");
        TodoResponse res = todoService.addTask(todoRequest2, response.getId());
        assertEquals(2, todoRepository.count());
        MarkTaskRequest request = new MarkTaskRequest();
        request.setTaskId(res.getId());
        TodoResponse res2 = todoService.markAsCompleted(request.getTaskId(), response.getId());
        assertEquals(Status.CHECKED, res2.getStatus());
    }
}