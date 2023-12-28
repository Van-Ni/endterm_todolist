package com.alibou.security.services;

import com.alibou.security.dto.TaskRequest;
import com.alibou.security.dto.TaskResponse;
import com.alibou.security.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    // List<Task> getAllTasks();
    List<TaskResponse> getAllTasks(Integer userId);

    TaskRequest createTask(TaskRequest taskRequest);

    TaskResponse getTaskById(Long taskId);

    TaskResponse updateTaskStatus(Long taskId);

    TaskResponse addToImportant(Long taskId);

    List<TaskResponse> getImportantTasksForUser(Integer userId);

    TaskResponse addToMyDay(Long taskId);

    List<TaskResponse> getMyDayTasksForUser(Integer userId);

    TaskResponse updateTaskDetails(Long taskId, TaskRequest updatedTask);

    void deleteTask(Long taskId);
}
