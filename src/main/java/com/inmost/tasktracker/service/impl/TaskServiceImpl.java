package com.inmost.tasktracker.service.impl;

import com.inmost.tasktracker.model.Task;
import com.inmost.tasktracker.repository.TaskRepository;
import com.inmost.tasktracker.service.TaskService;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private static final String USER_ID = "user_id";
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task get(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new RuntimeException("Failed to find a task with id: " + taskId));
    }

    @Override
    public void delete(long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getAll(String taskStatusName) {
        return taskRepository.findTasksByStatus_StatusName(taskStatusName);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll(Sort.by(USER_ID).descending());
    }
}
