package com.alibou.security.models;
import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String note;

    // Một Task thuộc về một người dùng
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isImportant;

    private boolean isAddedToMyDay;

    @Enumerated(EnumType.STRING)
    private RepeatType repeatType;
    public enum RepeatType {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
    private boolean isCompleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
