package com.alibou.security.models;
import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.Data;

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
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    public enum TaskStatus {
        COMPLETE,
        IN_PROGRESS,
        PENDING,
    }
}
