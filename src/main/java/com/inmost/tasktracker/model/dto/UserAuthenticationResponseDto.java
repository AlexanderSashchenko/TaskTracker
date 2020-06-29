package com.inmost.tasktracker.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAuthenticationResponseDto {
    private final String jwt;
}
