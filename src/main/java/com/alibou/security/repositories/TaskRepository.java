package com.alibou.security.repositories;

import com.alibou.security.models.Task;
import com.alibou.security.user.User;
import jakarta.validation.constraints.AssertFalse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserAndIsImportant(User user, boolean isImportant);
}