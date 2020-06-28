package com.inmost.tasktracker.model.dto;

import lombok.Data;

@Data
public class TaskResponseDto {
    private long taskId;
    private String title;
    private String description;
    private String statusName;
    private long authorId;
    private long assigneeId;
}
