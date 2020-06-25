package com.inmost.tasktracker.service;

import com.inmost.tasktracker.model.Task;
import java.util.List;

public interface TaskService {
    Task add(Task task);

    Task update(Task task);

    Task get(long taskId);

    void delete(long taskId);

    List<Task> getAll(String taskStatusName);

    List<Task> getAll();
}
