package com.example.pratica_devops.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes do CourseRecommendation
 */
@DisplayName("CourseRecommendation Tests")
class CourseRecommendationTest {
    
    @Test
    @DisplayName("Deve criar recomendação corretamente")
    void shouldCreateRecommendation() {
        // Arrange & Act
        CourseRecommendation rec = new CourseRecommendation(
            "Java Avançado",
            "Curso completo de Java",
            "Aprofunda conhecimento"
        );
        
        // Assert
        assertThat(rec.getCourseName()).isEqualTo("Java Avançado");
        assertThat(rec.getDescription()).isEqualTo("Curso completo de Java");
        assertThat(rec.getReasoning()).isEqualTo("Aprofunda conhecimento");
    }
    
    @Test
    @DisplayName("Deve implementar equals corretamente")
    void shouldImplementEquals() {
        CourseRecommendation r1 = new CourseRecommendation("Java", "Desc", "Reason");
        CourseRecommendation r2 = new CourseRecommendation("Java", "Desc", "Reason");
        CourseRecommendation r3 = new CourseRecommendation("Python", "Desc", "Reason");
        
        assertThat(r1).isEqualTo(r2);
        assertThat(r1).isNotEqualTo(r3);
    }
}
