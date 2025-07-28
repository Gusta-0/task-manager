package com.gustavo.taskapi.taskmanager.domain.repository;

import com.gustavo.taskapi.taskmanager.domain.entity.Task;
import com.gustavo.taskapi.taskmanager.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserId(Long userId);
}
