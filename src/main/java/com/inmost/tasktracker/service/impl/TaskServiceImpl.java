package com.inmost.tasktracker.service.impl;

import com.inmost.tasktracker.exception.DataProcessingException;
import com.inmost.tasktracker.model.Task;
import com.inmost.tasktracker.repository.StatusRepository;
import com.inmost.tasktracker.repository.TaskRepository;
import com.inmost.tasktracker.service.TaskService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private static final String SORTING_TYPE_ASC = "asc";
    private static final String SORTING_TYPE_DESC = "desc";
    private static final String VIEW = "View";

    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;

    public TaskServiceImpl(TaskRepository taskRepository, StatusRepository statusRepository) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Task add(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(statusRepository.getStatusByStatusName(VIEW));
        }
        return taskRepository.save(task);
    }

    @Override
    public Task get(long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new DataProcessingException("Failed to find a task with id: " + taskId));
    }

    @Override
    public List<Task> getAllSortedFilterByStatus(String sortingType, String statusName) {
        if (sortingType.toLowerCase().equals(SORTING_TYPE_DESC)) {
            return taskRepository.findAllByStatus_StatusNameOrderByAssignee_UserIdDesc(statusName);
        } else if (sortingType.toLowerCase().equals(SORTING_TYPE_ASC)) {
            return taskRepository.findAllByStatus_StatusNameOrderByAssignee_UserIdAsc(statusName);
        } else {
            throw new DataProcessingException("Wrong parameter for sorting type has been inputted "
                    + ". Please choose either \"asc\" for ascending order or \"desc\" for "
                    + " descending order.");
        }
    }

    @Override
    public List<Task> getAllSorted(String sortingType) {
        if (sortingType.toLowerCase().equals(SORTING_TYPE_DESC)) {
            return taskRepository.getAllByAssigneeNotNullOrderByAssignee_UserIdDesc();
        } else if (sortingType.toLowerCase().equals(SORTING_TYPE_ASC)) {
            return taskRepository.getAllByAssigneeNotNullOrderByAssignee_UserIdAsc();
        } else {
            throw new DataProcessingException("Wrong parameter for sorting type has been inputted "
                    + ". Please choose either \"asc\" for ascending order or \"desc\" for "
                    + " descending order.");
        }
    }

    @Override
    public Task update(Task task) {
        if (taskRepository.findById(task.getTaskId()).isPresent()) {
            return taskRepository.save(task);
        } else {
            throw new DataProcessingException("Failed to find a task with id: " + task.getTaskId());
        }
    }

    @Override
    public void delete(long taskId) {
        if (taskRepository.findById(taskId).isPresent()) {
            taskRepository.deleteById(taskId);
        } else {
            throw new DataProcessingException("Failed to find a task with id: " + taskId);
        }
    }
}
