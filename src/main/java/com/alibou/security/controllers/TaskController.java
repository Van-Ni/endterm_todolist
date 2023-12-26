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
    private final TaskService taskService;
    private final TaskRepository taskRepository;
    @Autowired
    public TaskController(TaskService taskService,  TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable("id") Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .note(task.getNote())
                .isImportant(task.isImportant())
                .isAddedToMyDay(task.isAddedToMyDay())
                .repeatType(task.getRepeatType().toString())
                .status(task.getStatus().toString())
                .build();
    }

    @PostMapping
    public TaskRequest createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }


}
