package com.taskagile.domain.model.common.events;

public interface DomainEventPublisher {
     void publish(DomainEvent event);
}
