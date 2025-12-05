package com.example.pratica_devops.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.pratica_devops.domain.events.CourseCompletedEvent;
import com.example.pratica_devops.domain.service.EventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Adapter (Infrastructure) - Publicador de eventos via RabbitMQ
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQEventPublisher implements EventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    
    @Override
    public void publish(CourseCompletedEvent event) {
        try {
            log.info("Publicando evento: Aluno {} concluiu curso {} ({})", 
                    event.getStudentId(), event.getCourseId(), event.getCourseName());
            
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_NAME,
                    RabbitMQConfig.ROUTING_KEY,
                    event
            );
            
            log.info("Evento publicado com sucesso");
        } catch (Exception e) {
            log.error("Erro ao publicar evento: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao publicar evento", e);
        }
    }
}
