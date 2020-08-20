package com.taskagile.domain.model.user;

import com.taskagile.domain.model.user.events.UserRegisteredEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventPublisher {
     private static final Logger log = LoggerFactory.getLogger(UserRegisteredEvent.class);

     @EventListener(UserRegisteredEvent.class)
     public void handleEvent(UserRegisteredEvent event) {
          log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());
     }
}
