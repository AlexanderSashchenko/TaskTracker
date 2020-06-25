package com.inmost.tasktracker.repository;

import com.inmost.tasktracker.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByStatus_StatusName(String taskStatusName);
}

