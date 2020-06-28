package com.inmost.tasktracker.service;

import com.inmost.tasktracker.model.Task;
import java.util.List;

public interface TaskService {
    Task add(Task task);

    Task get(long taskId);

    List<Task> getAllSortedFilterByStatus(String sortingType, String taskStatusName);

    List<Task> getAllSorted(String sortingType);

    Task update(Task task);

    void delete(long taskId);
}
