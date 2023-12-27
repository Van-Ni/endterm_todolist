package com.alibou.security.controllers;

import com.alibou.security.dto.TaskRequest;
import com.alibou.security.dto.TaskResponse;
import com.alibou.security.models.Task;
import com.alibou.security.repositories.TaskRepository;
import com.alibou.security.services.TaskService;
import com.alibou.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
    }

    @GetMapping("/user/{id}")
    public List<TaskResponse> getAllTasks(@PathVariable("id") Integer userId) {
        return taskService.getAllTasks(userId);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable("id") Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PostMapping
    public TaskRequest createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/{id}/status")
    public TaskResponse updateTaskStatus(
            @PathVariable("id") Long taskId,
            @RequestParam("status") String newStatus) {
        return taskService.updateTaskStatus(taskId, newStatus);
    }

    @PutMapping("/{id}/add-to-important")
    public TaskResponse addToImportant(@PathVariable("id") Long taskId) {
        return taskService.addToImportant(taskId);
    }

    @GetMapping("/user/{id}/important")
    public List<TaskResponse> getImportantTasksForUser(@PathVariable("id") Integer userId) {
        return taskService.getImportantTasksForUser(userId);
    }

    @PutMapping("/{id}/add-to-myday")
    public TaskResponse addToMyDay(@PathVariable("id") Long taskId) {
        return taskService.addToMyDay(taskId);
    }

    @GetMapping("/user/{id}/myday")
    public List<TaskResponse> getMyDayTasksForUser(@PathVariable("id") Integer userId) {
        return taskService.getMyDayTasksForUser(userId);
    }


}
