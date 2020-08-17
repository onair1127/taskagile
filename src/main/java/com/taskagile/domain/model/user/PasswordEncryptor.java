package com.taskagile.domain.model.user;

public interface PasswordEncryptor {
     String encrypt(String rawPassword);
}
