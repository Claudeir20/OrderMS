package com.example.OrderMS.infra.events;

import com.example.OrderMS.core.events.DomainEvent;
import com.example.OrderMS.core.events.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpringEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            applicationEventPublisher.publishEvent(event);
        }
    }
}
