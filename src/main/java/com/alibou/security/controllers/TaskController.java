package com.alibou.security.controllers;

import com.alibou.security.dto.TaskRequest;
import com.alibou.security.models.Task;
import com.alibou.security.services.TaskService;
import com.alibou.security.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/get/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping
    public TaskRequest createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }


}
