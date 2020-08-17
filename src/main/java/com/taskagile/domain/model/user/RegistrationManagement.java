package com.taskagile.domain.model.user;

import com.taskagile.domain.model.user.exception.EmailAddressExistsException;
import com.taskagile.domain.model.user.exception.RegistrationException;
import com.taskagile.domain.model.user.exception.UsernameExistsException;

import org.springframework.stereotype.Component;

@Component
public class RegistrationManagement {

     private UserRepository repository;
     private PasswordEncryptor encryptor;

     public RegistrationManagement(UserRepository repository, PasswordEncryptor encryptor) {
          this.repository = repository;
          this.encryptor = encryptor;
     }

     public User register(
          String username,
          String emailAddress,
          String password
     ) throws RegistrationException {
          User existingUser = repository.findByUsername(username);

          if(existingUser != null) {
               throw new UsernameExistsException();
          }

          existingUser = repository.findByEmailAddress(emailAddress.toLowerCase());

          if(existingUser != null) {
               throw new EmailAddressExistsException();
          }

          String encryptedPassword = encryptor.encrypt(password);
          User newUser = User.create(username, emailAddress.toLowerCase(), encryptedPassword);
          repository.save(newUser);
          return newUser;
     }
}
