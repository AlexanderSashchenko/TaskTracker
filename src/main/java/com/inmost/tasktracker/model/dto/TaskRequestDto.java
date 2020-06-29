package com.inmost.tasktracker.model.dto;

import com.inmost.tasktracker.validation.StatusValidator;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDto {
    @NotBlank(message = "Task title is a mandatory field")
    private String title;
    @NotBlank(message = "Task description is a mandatory field")
    private String description;
    @StatusValidator
    private String statusName;
}
