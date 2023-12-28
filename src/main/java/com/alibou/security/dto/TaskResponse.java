package com.alibou.security.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

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
    private LocalDate deadline;
    private Instant createdAt;
    private Instant lastUpdatedAt;

}