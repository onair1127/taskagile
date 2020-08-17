package com.taskagile.domain.application.impl;

import static org.mockito.Mockito.mock;

import com.taskagile.domain.model.common.events.DomainEventPublisher;
import com.taskagile.domain.model.common.mail.MailManager;
import com.taskagile.domain.model.user.RegistrationManagement;
import com.taskagile.domain.model.user.exception.RegistrationException;

import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTests {
     private RegistrationManagement registrationManagementMock;
     private DomainEventPublisher eventPublisherMock;
     private MailManager mailManagerMock;
     private UserServiceImpl instance;

     @Before
     public void setup() {
          registrationManagementMock = mock(RegistrationManagement.class);
          eventPublisherMock = mock(DomainEventPublisher.class);
          mailManagerMock = mock(MailManager.class);
          instance = new UserServiceImpl(registrationManagementMock,
                                             eventPublisherMock,
                                             mailManagerMock);
     }

     @Test(expected = IllegalArgumentException.class)
     public void register_nullCommand_shouldFail() throws RegistrationException {
          instance.register(null);
     }

}
