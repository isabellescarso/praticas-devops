package com.example.pratica_devops.infrastructure.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.pratica_devops.domain.events.CourseCompletedEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * Consumer (Infrastructure) - Listener de conclusão de cursos
 */
@Slf4j
@Component
public class CourseCompletionListener {
     
   @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleCourseCompletion(CourseCompletedEvent event) {
        log.info("════════════════════════════════════════════════════════");
        log.info("📜 GERANDO CERTIFICADO");
        log.info("════════════════════════════════════════════════════════");
        log.info("Aluno ID: {}", event.getStudentId());
        log.info("Curso: {} (ID: {})", event.getCourseName(), event.getCourseId());
        log.info("Data de Conclusão: {}", event.getCompletedAt());
        log.info("════════════════════════════════════════════════════════");
        
        System.out.println("\n🎓 CERTIFICADO GERADO PARA ALUNO " + event.getStudentId() 
                + " - CURSO: " + event.getCourseName() + "\n");
    }
}
