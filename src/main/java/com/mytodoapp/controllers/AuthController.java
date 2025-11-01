package com.mytodoapp.controllers;

import com.mytodoapp.data.models.User;
import com.mytodoapp.dtos.requests.LoginRequest;
import com.mytodoapp.dtos.requests.UserRequest;
import com.mytodoapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.registeredUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session){
        User user = userService.login(loginRequest); // No longer Optional

        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());

        Map<String, String> userMap = new HashMap<>();
        userMap.put("id", user.getId().toString());
        userMap.put("username", user.getUsername());

        return ResponseEntity.ok().header("Authorization", session.getId()).body(userMap);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("Not logged in");
        }
        Map<String, String> user = new HashMap<>();
        user.put("id", userId);
        user.put("username", session.getAttribute("username").toString() );
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return ResponseEntity.status(401).body("Not logged in");
        }
        userService.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
