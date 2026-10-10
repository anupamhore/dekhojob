package io.github.anupamhore.dekhojob.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest (

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max=255, message = "Email must be at most 255 characters")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min=8, max=72, message = "Password must be 8 to 72 characters long")
    String password,

    @NotBlank(message = "First name is required")
    @Size(max=100, message = "First name must be at most 100 characters")
    String firstName,

    @NotBlank(message = "Last name is required")
    @Size(max=100, message = "Last name must be at most 100 characters")
    String lastName
){}
