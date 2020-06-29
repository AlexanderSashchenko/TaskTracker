package com.inmost.tasktracker.controller;

import com.inmost.tasktracker.model.Task;
import com.inmost.tasktracker.model.User;
import com.inmost.tasktracker.model.dto.TaskRequestDto;
import com.inmost.tasktracker.model.dto.TaskResponseDto;
import com.inmost.tasktracker.repository.StatusRepository;
import com.inmost.tasktracker.service.TaskService;
import com.inmost.tasktracker.service.UserService;
import com.inmost.tasktracker.validation.SortingTypeValidator;
import com.inmost.tasktracker.validation.StatusValidator;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final StatusRepository statusRepository;

    public TaskController(TaskService taskService,
                          UserService userService,
                          ModelMapper modelMapper,
                          StatusRepository statusRepository) {
        this.taskService = taskService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.statusRepository = statusRepository;
    }

    @PostMapping
    public TaskResponseDto add(@Valid @RequestBody TaskRequestDto taskRequestDto,
                               Principal principal) {
        User user = userService.getByUsername(principal.getName());
        Task task = mapTaskRequestDtoToTask(taskRequestDto);
        task.setStatus(statusRepository.getStatusByStatusName(taskRequestDto.getStatusName()));
        task.setAuthor(userService.getById(user.getUserId()));
        task.setAssignee(userService.getById(user.getUserId()));
        return mapTaskToTaskResponseDto(taskService.add(task));
    }

    @GetMapping("/{task-id}")
    public TaskResponseDto get(@PathVariable("task-id") @Min(value = 1,
            message = "Task id should not be less than 1") long taskId) {
        return mapTaskToTaskResponseDto(taskService.get(taskId));
    }

    @GetMapping()
    public List<TaskResponseDto> getAll(@RequestParam(value = "sort")
                                        @SortingTypeValidator String sortingType,
                                        @RequestParam(value = "status", required = false)
                                        @StatusValidator String statusName) {
        if (statusName == null) {
            return taskService.getAllSorted(sortingType).stream()
                    .map(this::mapTaskToTaskResponseDto)
                    .collect(Collectors.toList());
        }
        return taskService.getAllSortedFilterByStatus(sortingType, statusName).stream()
                .map(this::mapTaskToTaskResponseDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{task-id}/status")
    public TaskResponseDto updateStatus(@PathVariable("task-id") @Min(value = 1,
            message = "Task id should not be less than 1") long taskId,
                                        @RequestParam(name = "status")
                                        @StatusValidator String newStatusName) {
        Task task = taskService.get(taskId);
        task.setStatus(statusRepository.getStatusByStatusName(newStatusName));
        return mapTaskToTaskResponseDto(taskService.update(task));
    }

    @PutMapping("/{task-id}/assignee")
    public TaskResponseDto updateAssignee(@PathVariable("task-id") @Min(value = 1,
            message = "Task id should not be less than 1") long taskId,
                                          @RequestParam(name = "assignee")
                                          @Min(1) long assigneeId) {
        Task task = taskService.get(taskId);
        task.setAssignee(userService.getById(assigneeId));
        return mapTaskToTaskResponseDto(taskService.update(task));
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity<Void> delete(@PathVariable("task-id") @Min(value = 1,
            message = "Task id should not be less than 1") long taskId) {
        taskService.delete(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private TaskResponseDto mapTaskToTaskResponseDto(Task task) {
        return modelMapper.map(task, TaskResponseDto.class);
    }

    private Task mapTaskRequestDtoToTask(TaskRequestDto taskRequestDto) {
        return modelMapper.map(taskRequestDto, Task.class);
    }
}
