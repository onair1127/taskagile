package com.taskagile.domain.model.common.events;

import org.springframework.context.ApplicationEvent;

public abstract class DomainEvent extends ApplicationEvent {

     /**
     *
     */
     private static final long serialVersionUID = -8059791903815620612L;

     public DomainEvent(Object source) {
          super(source);
     }

     public long occuredAt() {
          return occuredAt();
     }
}
