package com.example.pratica_devops.infrastructure.messaging;

import com.example.pratica_devops.domain.events.CourseCompletedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Testes do RabbitMQEventPublisher
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("RabbitMQ Event Publisher Tests")
class RabbitMQEventPublisherTest {
    
    @Mock
    private RabbitTemplate rabbitTemplate;
    
    @InjectMocks
    private RabbitMQEventPublisher publisher;
    
    @Test
    @DisplayName("Deve publicar evento com sucesso")
    void shouldPublishEventSuccessfully() {
        // Arrange
        CourseCompletedEvent event = CourseCompletedEvent.create(1L, 100L, "Java Avançado");
        
        // Act
        publisher.publish(event);
        
        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(
                anyString(),
                anyString(),
                any(CourseCompletedEvent.class)
        );
    }
    
    @Test
    @DisplayName("Deve lançar exceção quando RabbitMQ falha")
    void shouldThrowExceptionWhenRabbitMQFails() {
        // Arrange
        CourseCompletedEvent event = CourseCompletedEvent.create(1L, 100L, "Python");
        
        doThrow(new RuntimeException("Connection error"))
                .when(rabbitTemplate)
                .convertAndSend(anyString(), anyString(), any(Object.class));
        
        // Act & Assert
        assertThatThrownBy(() -> publisher.publish(event))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Falha ao publicar evento");
    }
}
