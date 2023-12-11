package com.alibou.security.services;

import com.alibou.security.models.Task;

import java.util.List;

public interface  TaskService {
    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Long id);
}
