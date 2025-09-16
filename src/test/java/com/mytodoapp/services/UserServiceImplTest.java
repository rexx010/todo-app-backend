package com.mytodoapp.services;

import com.mytodoapp.data.models.User;
import com.mytodoapp.data.repositories.UserRepository;
import com.mytodoapp.dtos.reponses.UserReponse;
import com.mytodoapp.dtos.requests.LoginRequest;
import com.mytodoapp.dtos.requests.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
     void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testThatUserRepositoryCanAdd(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Rexx01");
        userRequest.setEmail("grexx200@gmail.com");
        userRequest.setPassword("12345");
        userService.registeredUser(userRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testThatRegisteredInfoIsReturned(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Rexx01");
        userRequest.setEmail("grexx200@gmail.com");
        userRequest.setPassword("12345");

        UserReponse response = userService.registeredUser(userRequest);
        assertEquals("Rexx01", response.getUsername());
        assertEquals("grexx200@gmail.com", response.getEmail());
    }

    @Test
    public void testThatUserCantRegisterWithTheSameEmailTwice(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Rexx01");
        userRequest.setEmail("grexx200@gmail.com");
        userRequest.setPassword("12345");
        userService.registeredUser(userRequest);

        assertEquals(1, userRepository.count());

        userRequest.setUsername("Rexx01");
        userRequest.setEmail("grexx200@gmail.com");
        userRequest.setPassword("12345");
        assertThrows(RuntimeException.class, () -> userService.registeredUser(userRequest));
    }

    @Test
    public void testThatUserCantRegisterWithTheSameUsernameTwice(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Rexx01");
        userRequest.setEmail("grexx200@gmail.com");
        userRequest.setPassword("12345");
        userService.registeredUser(userRequest);

        assertEquals(1, userRepository.count());

        userRequest.setUsername("Rexx01");
        userRequest.setEmail("grexx@gmail.com");
        userRequest.setPassword("12345");
        assertThrows(RuntimeException.class, () -> userService.registeredUser(userRequest));
    }

    @Test
    public void testThatUserCanLoginWithTheSameUsernameAndPassword(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Rexx01");
        userRequest.setEmail("grexx200@gmail.com");
        userRequest.setPassword("12345");
        userService.registeredUser(userRequest);

        assertEquals(1, userRepository.count());
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Rexx01");
        loginRequest.setPassword("12345");
        User user = userService.login(loginRequest).orElseThrow(() -> new RuntimeException("No user found"));
        assertEquals("Rexx01", user.getUsername());
    }

    @Test
    public void testThatUserCanLoginWithoutARegisteredUser(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("Rexx");
        userRequest.setEmail("grexx200@gmail.com");
        userRequest.setPassword("12345");
        userService.registeredUser(userRequest);

        assertEquals(1, userRepository.count());
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Rexx01");
        loginRequest.setPassword("12345");
        assertThrows(RuntimeException.class, () -> {userService.login(loginRequest)
                    .orElseThrow(() -> new RuntimeException("No user found"));
        });
    }

}