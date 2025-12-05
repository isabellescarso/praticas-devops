package com.example.pratica_devops.domain.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Domain Event - Conclusão de curso por um aluno
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCompletedEvent {
    private Long studentId;
    private Long courseId;
    private String courseName;
    private LocalDateTime completedAt;
    
    public static CourseCompletedEvent create(Long studentId, Long courseId, String courseName) {
        return new CourseCompletedEvent(studentId, courseId, courseName, LocalDateTime.now());
    }
}
