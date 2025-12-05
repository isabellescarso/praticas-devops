package com.example.pratica_devops.infrastructure.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração RabbitMQ - Infraestrutura
 */
@Configuration
public class RabbitMQConfig {
    
    public static final String EXCHANGE_NAME = "courses.exchange";
    public static final String QUEUE_NAME = "course-completion-queue";
    public static final String ROUTING_KEY = "course.completed";
    
    @Bean
    public TopicExchange coursesExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
    
    @Bean
    public Queue courseCompletionQueue() {
        return new Queue(QUEUE_NAME, true); // durable
    }
    
    @Bean
    public Binding binding(Queue courseCompletionQueue, TopicExchange coursesExchange) {
        return BindingBuilder
                .bind(courseCompletionQueue)
                .to(coursesExchange)
                .with(ROUTING_KEY);
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                        Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
