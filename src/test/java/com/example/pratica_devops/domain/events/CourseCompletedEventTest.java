package com.example.pratica_devops.domain.events;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testes do CourseCompletedEvent
 */
@DisplayName("CourseCompletedEvent Tests")
class CourseCompletedEventTest {
    
    @Test
    @DisplayName("Deve criar evento com factory method")
    void shouldCreateEventWithFactoryMethod() {
        // Arrange & Act
        CourseCompletedEvent event = CourseCompletedEvent.create(1L, 100L, "Java Básico");
        
        // Assert
        assertThat(event.getStudentId()).isEqualTo(1L);
        assertThat(event.getCourseId()).isEqualTo(100L);
        assertThat(event.getCourseName()).isEqualTo("Java Básico");
        assertThat(event.getCompletedAt()).isNotNull();
        assertThat(event.getCompletedAt()).isBefore(LocalDateTime.now().plusSeconds(1));
    }
    
    @Test
    @DisplayName("Deve criar evento completo")
    void shouldCreateCompleteEvent() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        
        // Act
        CourseCompletedEvent event = new CourseCompletedEvent(5L, 200L, "Spring Boot", now);
        
        // Assert
        assertThat(event.getStudentId()).isEqualTo(5L);
        assertThat(event.getCourseId()).isEqualTo(200L);
        assertThat(event.getCourseName()).isEqualTo("Spring Boot");
        assertThat(event.getCompletedAt()).isEqualTo(now);
    }
}
