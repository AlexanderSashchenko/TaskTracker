package com.inmost.tasktracker.model.dto;

import com.inmost.tasktracker.validation.StatusValidator;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TaskRequestDto {
    @NotBlank(message = "Task title is a mandatory field")
    private String title;
    @NotBlank(message = "Task description is a mandatory field")
    private String description;
    @StatusValidator
    private String statusName;
}
