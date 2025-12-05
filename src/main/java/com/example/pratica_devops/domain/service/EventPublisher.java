package com.example.pratica_devops.domain.service;

import com.example.pratica_devops.domain.events.CourseCompletedEvent;

//Domain - Serviço de publicação de eventos
public interface EventPublisher {
    void publish(CourseCompletedEvent event);
}
