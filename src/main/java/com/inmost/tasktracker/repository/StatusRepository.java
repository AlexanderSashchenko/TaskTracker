package com.inmost.tasktracker.repository;

import com.inmost.tasktracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status getStatusByStatusName(String statusName);
}
