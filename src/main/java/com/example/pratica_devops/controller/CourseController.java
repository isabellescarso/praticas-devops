package com.example.pratica_devops.controller;

import com.example.pratica_devops.application.usecase.CompleteCourseUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Controller para demonstração de conclusão de cursos
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Tag(name = "Courses", description = "Gerenciamento de conclusão de cursos")
public class CourseController {
    
    private final CompleteCourseUseCase completeCourseUseCase;
    
    @PostMapping("/complete")
    @Operation(summary = "Marcar curso como concluído", 
               description = "Marca um curso como concluído e publica evento no RabbitMQ")
    public ResponseEntity<String> completeCourse(
            @RequestParam Long studentId,
            @RequestParam Long courseId,
            @RequestParam String courseName) {
        
        completeCourseUseCase.execute(studentId, courseId, courseName);
        
        return ResponseEntity.ok("Curso marcado como concluído. Certificado será gerado em breve.");
    }
}
