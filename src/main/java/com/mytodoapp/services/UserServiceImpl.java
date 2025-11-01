package com.mytodoapp.services;

import com.mytodoapp.data.models.User;
import com.mytodoapp.data.repositories.UserRepository;
import com.mytodoapp.dtos.reponses.UserResponse;
import com.mytodoapp.dtos.requests.LoginRequest;
import com.mytodoapp.dtos.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(14);

    @Override
    public UserResponse registeredUser(UserRequest userRequest) {
        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()){
            throw new RuntimeException("User with this username already exists");
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User savedUser = userRepository.save(user);
        UserResponse userReponse = new UserResponse();
        userReponse.setId(savedUser.getId());
        userReponse.setUsername(savedUser.getUsername());
        userReponse.setEmail(savedUser.getEmail());
        return userReponse;
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Username not found"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }

    @Override
    public Optional<User> getUserById(String id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }


}
