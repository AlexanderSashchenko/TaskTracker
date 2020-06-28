package com.inmost.tasktracker.model.dto;

import lombok.Data;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private String statusName;
}
