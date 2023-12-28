package com.alibou.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String note;
    private boolean isImportant;
    private boolean isAddedToMyDay;
    private boolean isCompleted;
    private String repeatType;
}