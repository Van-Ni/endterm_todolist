package com.alibou.security.models;
import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.Instant;
import java.time.LocalDate;

import static org.hibernate.generator.EventType.INSERT;
import static org.hibernate.generator.EventType.UPDATE;

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

    @Column(name = "deadline")
    private LocalDate deadline;

    @CurrentTimestamp(event = INSERT)
    public Instant createdAt;

    @CurrentTimestamp(event = {INSERT, UPDATE})
    public Instant lastUpdatedAt;
}
