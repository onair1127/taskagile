package com.taskagile.domain.model.user.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventHandler {

     private static final Logger log = LoggerFactory.getLogger(UserRegisteredEventHandler.class);

     @EventListener(UserRegisteredEvent.class)
     public void handleEvent(UserRegisteredEvent event) {
          log.debug("Handling `{}` registration event", event.getUser().getEmailAddress());
     }
}
