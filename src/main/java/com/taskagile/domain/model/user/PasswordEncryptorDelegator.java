package com.taskagile.domain.model.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptorDelegator implements PasswordEncryptor {

     private PasswordEncoder passwordEncoder;

     public PasswordEncryptorDelegator(PasswordEncoder passwordEncoder) {
          this.passwordEncoder = passwordEncoder;
     }

     @Override
     public String encrypt(String rawPassword) {
          // Encrypt의 행동은 Spring에 위임할건데 그건 나중에 ㅎ(10장)
          return passwordEncoder.encode(rawPassword);
     }

}
