package com.gustavo.taskapi.taskmanager.domain.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter//(AccessLevel.PROTECTED) // setter privado só para uso interno
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor//(access = AccessLevel.PRIVATE) // construtor privado para uso do Builder
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void assignToUser(User user) {
        this.user = user;
    }

//    public static Task creat//        Task task = new Task();
////        task.title = title;
////        task.description = description;
////        task.dueDate = dueDate;
////        task.status = TaskStatus.TODO;
////        task.user = user;
////        return task;
////    }
////
////    public void update(String title, String description, LocalDateTime dueDate, User user) {
////        this.title = title;
////        this.description = description;
////        this.dueDate = dueDate;
////        this.user = user;
////    }
////
////    public void updateStatus(TaskStatus status) {
////        this.status = status;
////    }e(String title, String description, LocalDateTime dueDate, User user) {

}

