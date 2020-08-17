package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.RegistrationCommand;
import com.taskagile.domain.model.user.exception.RegistrationException;

public interface UserService {
    void register(RegistrationCommand command) throws RegistrationException;
}
