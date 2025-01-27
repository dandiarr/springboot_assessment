package com.raizal.springbootassessment.repository;

import com.raizal.springbootassessment.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository <Task, Long> {
    public Optional<Task> findByTask (String task);
    public List <Task> findByCompletedTrue();
    public List <Task> findByCompletedFalse();
    public List <Task> findAll();
    public Task getById(Long id);
}
