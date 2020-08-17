package com.taskagile.domain.model.common.events;

import com.taskagile.domain.model.user.events.UserRegisteredEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DefaultDomainEventPublisher implements DomainEventPublisher {

     @Autowired
     private ApplicationEventPublisher actualPublisher;

     @Override
     public void publish(DomainEvent event) {
          actualPublisher.publishEvent(event);
     }

     @Override
     public void publish(UserRegisteredEvent event) {
          actualPublisher.publishEvent(event);
     }

}
