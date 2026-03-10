package com.example.OrderMS.core.events;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime occurredAt();
}
