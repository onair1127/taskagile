package com.taskagile.domain.model.user.events;

import com.taskagile.domain.model.common.events.DomainEvent;
import com.taskagile.domain.model.user.User;

import org.springframework.util.Assert;

public class UserRegisteredEvent extends DomainEvent {

     private static final long serialVersionUID = -4859731169503259134L;
     private User user;

     public UserRegisteredEvent(User user) {
          super(user);
          Assert.notNull(user, "Parameter `user` must not be null");

          this.user = user;
     }

     public User getUser() {
          return this.user;
     }

     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;

          UserRegisteredEvent that = (UserRegisteredEvent) o;
          return that.user.equals(this.user);
     }

     public int hashCode() {
          return this.user.hashCode();
     }

     public String toString() {
          return "UserRegisteredEvent{" +
            "user='" + this.user + '\'' +
            "timestamp='" + getTimestamp() + '\'' +
            '}';
     }
}
