package com.example.pratica_devops.domain;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes do StudentProfile
 */
@DisplayName("StudentProfile Tests")
class StudentProfileTest {
    
    @Test
    @DisplayName("Deve criar e usar perfil corretamente")
    void shouldCreateAndUseProfile() {
        // Arrange & Act
        StudentProfile profile = new StudentProfile(
            "João Silva",
            Arrays.asList("Java", "Spring Boot"),
            List.of("Curso Básico Java")
        );
        
        // Assert
        assertThat(profile.getStudentName()).isEqualTo("João Silva");
        assertThat(profile.getInterests()).containsExactly("Java", "Spring Boot");
        assertThat(profile.getCompletedCourses()).hasSize(1);
    }
    
    @Test
    @DisplayName("Deve implementar equals corretamente")
    void shouldImplementEquals() {
        StudentProfile p1 = new StudentProfile("João", Arrays.asList("Java"), List.of());
        StudentProfile p2 = new StudentProfile("João", Arrays.asList("Java"), List.of());
        StudentProfile p3 = new StudentProfile("Maria", Arrays.asList("Java"), List.of());
        
        assertThat(p1).isEqualTo(p2);
        assertThat(p1).isNotEqualTo(p3);
    }
}
