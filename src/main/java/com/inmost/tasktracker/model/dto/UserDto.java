package com.inmost.tasktracker.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
}
