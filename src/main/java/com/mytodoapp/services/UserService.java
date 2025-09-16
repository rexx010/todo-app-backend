package com.mytodoapp.services;

import com.mytodoapp.data.models.User;
import com.mytodoapp.dtos.reponses.UserReponse;
import com.mytodoapp.dtos.requests.LoginRequest;
import com.mytodoapp.dtos.requests.UserRequest;

import java.util.Optional;

public interface UserService {
    UserReponse registeredUser(UserRequest userRequest);
    Optional<User> login(LoginRequest loginRequest);
    Optional<User> getUserById(String id);
    void deleteById(String id);
}
