package com.example.OrderMS.core.events;

import java.util.List;

public interface EventPublisher {
    void publish(List<DomainEvent> events);
}
