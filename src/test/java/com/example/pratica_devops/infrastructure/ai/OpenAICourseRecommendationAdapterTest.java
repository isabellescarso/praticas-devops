package com.example.pratica_devops.infrastructure.ai;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pratica_devops.domain.CourseRecommendation;
import com.example.pratica_devops.domain.StudentProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Testes do OpenAICourseRecommendationAdapter
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("OpenAI Course Recommendation Adapter Tests")
class OpenAICourseRecommendationAdapterTest {
    
    @Mock
    private EducationalConsultantAI aiService;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    @DisplayName("Deve retornar mock quando sem API Key")
    void shouldReturnMockWhenNoApiKey() {
        // Arrange
        OpenAICourseRecommendationAdapter adapter = 
            new OpenAICourseRecommendationAdapter(null, objectMapper, "");
        
        StudentProfile profile = new StudentProfile(
            "João Silva", 
            Arrays.asList("Java", "Spring"), 
            List.of()
        );
        
        // Act
        List<CourseRecommendation> recommendations = adapter.recommendCourses(profile);
        
        // Assert
        assertThat(recommendations).isNotEmpty();
        assertThat(recommendations).hasSize(3);
    }
    
    @Test
    @DisplayName("Deve usar IA quando API Key configurada")
    void shouldUseAiWhenConfigured() throws Exception {
        // Arrange
        OpenAICourseRecommendationAdapter adapter = 
            new OpenAICourseRecommendationAdapter(aiService, objectMapper, "sk-test");
        
        StudentProfile profile = new StudentProfile(
            "Maria", 
            Arrays.asList("Python"), 
            List.of()
        );
        
        String mockResponse = """
            [
              {
                "courseName": "Python Avançado",
                "description": "Curso completo",
                "reasoning": "Aprofunda Python"
              }
            ]
            """;
        
        when(aiService.recommend(anyString(), anyString(), anyString()))
            .thenReturn(mockResponse);
        
        // Act
        List<CourseRecommendation> recommendations = adapter.recommendCourses(profile);
        
        // Assert
        assertThat(recommendations).hasSize(1);
        assertThat(recommendations.get(0).getCourseName()).isEqualTo("Python Avançado");
    }
    
    @Test
    @DisplayName("Deve fazer fallback quando IA falha")
    void shouldFallbackWhenAiFails() {
        // Arrange
        OpenAICourseRecommendationAdapter adapter = 
            new OpenAICourseRecommendationAdapter(aiService, objectMapper, "sk-test");
        
        StudentProfile profile = new StudentProfile(
            "Carlos", 
            Arrays.asList("Java"), 
            List.of()
        );
        
        when(aiService.recommend(anyString(), anyString(), anyString()))
            .thenThrow(new RuntimeException("API Error"));
        
        // Act
        List<CourseRecommendation> recommendations = adapter.recommendCourses(profile);
        
        // Assert
        assertThat(recommendations).isNotEmpty();
    }
}
