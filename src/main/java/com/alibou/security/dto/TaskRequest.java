package com.alibou.security.dto;

import com.alibou.security.models.Task;
import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String note;
    private Long user;
    private boolean isImportant;
    private boolean isAddedToMyDay;
    private Task.RepeatType repeatType;
    private Task.TaskStatus status;

}
