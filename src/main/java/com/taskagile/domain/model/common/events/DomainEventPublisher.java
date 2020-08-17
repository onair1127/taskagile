package com.taskagile.domain.model.common.events;

import com.taskagile.domain.model.user.events.UserRegisteredEvent;

public interface DomainEventPublisher {
     void publish(DomainEvent event);
     void publish(UserRegisteredEvent event);
}
