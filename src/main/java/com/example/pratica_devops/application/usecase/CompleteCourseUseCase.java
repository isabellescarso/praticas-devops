package com.example.pratica_devops.application.usecase;

import com.example.pratica_devops.domain.events.CourseCompletedEvent;
import com.example.pratica_devops.domain.service.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//Use Case (Application) - Marca curso como concluído e publica evento
@Slf4j
@Service
@RequiredArgsConstructor
public class CompleteCourseUseCase {
    
    private final EventPublisher eventPublisher;
    
    public void execute(Long studentId, Long courseId, String courseName) {
        log.info("Processando conclusão de curso: Aluno={}, Curso={}", studentId, courseId);
        
        // Publicar evento de domínio
        CourseCompletedEvent event = CourseCompletedEvent.create(studentId, courseId, courseName);
        eventPublisher.publish(event);
        
        log.info("Curso marcado como concluído com sucesso");
    }
}
