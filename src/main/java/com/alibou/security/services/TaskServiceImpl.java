package com.alibou.security.services;

import com.alibou.security.dto.TaskRequest;
import com.alibou.security.models.Task;
import com.alibou.security.repositories.TaskRepository;
import com.alibou.security.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }



    @Override
    public Optional<Task> findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task;
    }

    @Override
    public TaskRequest createTask(TaskRequest request) {
        Task task = new Task();
        BeanUtils.copyProperties(request, task);

        task.setUser(userRepository.getById(Math.toIntExact(request.getUser())));

        Task task2 = taskRepository.save(task);
        return request;
    }

}
