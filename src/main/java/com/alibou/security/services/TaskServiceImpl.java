package com.alibou.security.services;

import com.alibou.security.dto.TaskRequest;
import com.alibou.security.dto.TaskResponse;
import com.alibou.security.models.Task;
import com.alibou.security.repositories.TaskRepository;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
        private final TaskRepository taskRepository;
        private final UserRepository userRepository;

        @Autowired
        public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
                this.taskRepository = taskRepository;
                this.userRepository = userRepository;
        }

        @Override
        public List<TaskResponse> getAllTasks(Integer userId) {
                // return taskRepository.findAll();
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

                List<Task> tasks = taskRepository.findByUser(user);

                return tasks.stream()
                                .map(task -> TaskResponse.builder()
                                                .id(task.getId())
                                                .title(task.getTitle())
                                                .note(task.getNote())
                                                .isImportant(task.isImportant())
                                                .isAddedToMyDay(task.isAddedToMyDay())
                                                .repeatType(task.getRepeatType().toString())
                                                .isCompleted(task.isCompleted())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public TaskResponse getTaskById(Long taskId) {
                Task task = taskRepository.findById(taskId)
                                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

                return TaskResponse.builder()
                                .id(task.getId())
                                .title(task.getTitle())
                                .note(task.getNote())
                                .isImportant(task.isImportant())
                                .isAddedToMyDay(task.isAddedToMyDay())
                                .repeatType(task.getRepeatType().toString())
                                .isCompleted(task.isCompleted())
                                .build();
        }

        @Override
        public TaskRequest createTask(TaskRequest request) {
                Task task = new Task();
                BeanUtils.copyProperties(request, task);

                task.setUser(userRepository.getById(Math.toIntExact(request.getUser())));

                Task task2 = taskRepository.save(task);
                return request;
        }

        @Override
        public TaskResponse updateTaskStatus(Long taskId) {
                Task task = taskRepository.findById(taskId)
                                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

                // Update the task status
                task.setCompleted(!task.isCompleted());

                // Save the updated task
                taskRepository.save(task);

                return TaskResponse.builder()
                                .id(task.getId())
                                .title(task.getTitle())
                                .note(task.getNote())
                                .isImportant(task.isImportant())
                                .isAddedToMyDay(task.isAddedToMyDay())
                                .repeatType(task.getRepeatType().toString())
                                .isCompleted(task.isCompleted())
                                .build();
        }

        @Override
        public TaskResponse addToImportant(Long taskId) {
                Task task = taskRepository.findById(taskId)
                                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

                // Update the task's isImportant property
                task.setImportant(true);

                // Save the updated task
                taskRepository.save(task);

                return TaskResponse.builder()
                                .id(task.getId())
                                .title(task.getTitle())
                                .note(task.getNote())
                                .isImportant(task.isImportant())
                                .isAddedToMyDay(task.isAddedToMyDay())
                                .repeatType(task.getRepeatType().toString())
                                .isCompleted(task.isCompleted())
                                .build();
        }

        @Override
        public List<TaskResponse> getImportantTasksForUser(Integer userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " +
                                                userId));

                List<Task> importantTasks = taskRepository.findByUserAndIsImportant(user,
                                true);

                return importantTasks.stream()
                                .map(task -> TaskResponse.builder()
                                                .id(task.getId())
                                                .title(task.getTitle())
                                                .note(task.getNote())
                                                .isImportant(task.isImportant())
                                                .isAddedToMyDay(task.isAddedToMyDay())
                                                .repeatType(task.getRepeatType().toString())
                                                .isCompleted(task.isCompleted())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public TaskResponse addToMyDay(Long taskId) {
                Task task = taskRepository.findById(taskId)
                                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

                // Update the task's isImportant property
                task.setAddedToMyDay(true);

                // Save the updated task
                taskRepository.save(task);

                return TaskResponse.builder()
                                .id(task.getId())
                                .title(task.getTitle())
                                .note(task.getNote())
                                .isImportant(task.isImportant())
                                .isAddedToMyDay(task.isAddedToMyDay())
                                .repeatType(task.getRepeatType().toString())
                                .isCompleted(task.isCompleted())
                                .build();
        }

        @Override
        public List<TaskResponse> getMyDayTasksForUser(Integer userId) {
                User user = userRepository.findById(userId)
                                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " +
                                                userId));

                List<Task> importantTasks = taskRepository.findByUserAndIsAddedToMyDay(user,
                                true);

                return importantTasks.stream()
                                .map(task -> TaskResponse.builder()
                                                .id(task.getId())
                                                .title(task.getTitle())
                                                .note(task.getNote())
                                                .isImportant(task.isImportant())
                                                .isAddedToMyDay(task.isAddedToMyDay())
                                                .repeatType(task.getRepeatType().toString())
                                                .isCompleted(task.isCompleted())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public TaskResponse updateTaskDetails(Long taskId, TaskRequest updatedTask) {
                Task task = taskRepository.findById(taskId)
                                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

                task.setTitle(updatedTask.getTitle());
                task.setNote(updatedTask.getNote());
                task.setRepeatType(updatedTask.getRepeatType());

                taskRepository.save(task);

                return TaskResponse.builder()
                                .id(task.getId())
                                .title(task.getTitle())
                                .note(task.getNote())
                                .isImportant(task.isImportant())
                                .isAddedToMyDay(task.isAddedToMyDay())
                                .repeatType(task.getRepeatType().toString())
                                .isCompleted(task.isCompleted())
                                .build();
        }

        @Override
        public void deleteTask(Long taskId) {
                Task task = taskRepository.findById(taskId)
                                .orElseThrow(() -> new NoSuchElementException("Task not found with ID: " + taskId));

                taskRepository.delete(task);
        }
}
