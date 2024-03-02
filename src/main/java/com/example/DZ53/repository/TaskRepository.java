package com.example.DZ53.repository;


import com.example.DZ53.model.Status;
import com.example.DZ53.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByStatus(Status status);

    Object saveAll();
}
