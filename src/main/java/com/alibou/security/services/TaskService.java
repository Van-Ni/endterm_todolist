package com.alibou.security.services;

import com.alibou.security.dto.TaskRequest;
import com.alibou.security.dto.TaskResponse;
import com.alibou.security.models.Task;

import java.util.List;
import java.util.Optional;

public interface  TaskService {
    // List<Task> getAllTasks();
    List<TaskResponse> getAllTasks(Integer userId);

    TaskRequest createTask(TaskRequest taskRequest);
    TaskResponse getTaskById(Long taskId);

    TaskResponse updateTaskStatus(Long taskId, String newStatus);

    TaskResponse addToImportant(Long taskId);

//    List<TaskResponse> getImportantTasksForUser(Long userId);
}
