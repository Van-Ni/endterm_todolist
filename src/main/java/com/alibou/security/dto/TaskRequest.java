package com.alibou.security.dto;

import com.alibou.security.models.Task;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private String title;
    private String note;
    private Long user;
    private boolean isImportant;
    private boolean isAddedToMyDay;
    private Task.RepeatType repeatType;
    private boolean isCompleted;
    private LocalDate deadline;
}
