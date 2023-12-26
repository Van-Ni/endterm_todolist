package com.alibou.security.services;

import com.alibou.security.dto.TaskRequest;
import com.alibou.security.models.Task;

import java.util.List;
import java.util.Optional;

public interface  TaskService {

    Optional<Task> findById(Long id);

    TaskRequest createTask(TaskRequest taskRequest);


}
