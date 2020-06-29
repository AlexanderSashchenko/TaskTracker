package com.inmost.tasktracker.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @NotBlank(message = "Username is a mandatory field")
    private String username;
    @NotBlank(message = "Password is a mandatory field")
    private String password;
}
