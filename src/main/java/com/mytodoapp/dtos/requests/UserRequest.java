package com.mytodoapp.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9]+@[A-Za-z]+\\.com$", message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
