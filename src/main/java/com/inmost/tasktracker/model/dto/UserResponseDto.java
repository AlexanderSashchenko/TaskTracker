package com.inmost.tasktracker.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserResponseDto {
    @Min(value = 1, message = "Minimum value for user id is 1")
    private long userId;
    @NotBlank(message = "First Name is a mandatory field")
    private String firstName;
    @NotBlank(message = "Last Name is a mandatory field")
    private String lastName;
    @Email(message = "Email format is not valid")
    private String email;
}
