package com.example.pratica_devops.controller;

import com.example.pratica_devops.domain.CourseRecommendation;
import com.example.pratica_devops.domain.StudentProfile;
import com.example.pratica_devops.domain.service.CourseRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller para recomendações de cursos com IA
@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@Tag(name = "Course Recommendations", description = "Recomendações de cursos usando IA")
public class CourseRecommendationController {
    
    private final CourseRecommendationService recommendationService;
    
    @PostMapping
    @Operation(summary = "Obter recomendações de cursos", 
               description = "Usa IA (OpenAI) para recomendar cursos baseado no perfil do aluno")
    public ResponseEntity<List<CourseRecommendation>> getRecommendations(
            @RequestBody StudentProfile profile) {
        
        List<CourseRecommendation> recommendations = recommendationService.recommendCourses(profile);
        
        return ResponseEntity.ok(recommendations);
    }
}
