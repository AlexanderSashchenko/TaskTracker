package com.inmost.tasktracker.model.dto;

import com.inmost.tasktracker.validation.UniqueUsernameValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotBlank(message = "First Name is a mandatory field")
    private String firstName;
    @NotBlank(message = "Last Name is a mandatory field")
    private String lastName;
    @Email(message = "Email format is not valid")
    private String email;
    @NotBlank(message = "Username is a mandatory field")
    @UniqueUsernameValidator
    private String username;
    @NotBlank(message = "Password is a mandatory field")
    private String password;
}
