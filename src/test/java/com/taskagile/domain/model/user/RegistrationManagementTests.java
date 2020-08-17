package com.taskagile.domain.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.taskagile.domain.model.user.exception.EmailAddressExistsException;
import com.taskagile.domain.model.user.exception.RegistrationException;
import com.taskagile.domain.model.user.exception.UsernameExistsException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

public class RegistrationManagementTests {
     private UserRepository repositoryMock;
     private RegistrationManagement instance;
     private PasswordEncryptor passwordEncryptorMock;

     @Before
     public void setup() {
          this.repositoryMock = mock(UserRepository.class);
          this.passwordEncryptorMock = mock(PasswordEncryptor.class);
          this.instance = new RegistrationManagement(repositoryMock, passwordEncryptorMock); //수정
     }

     @Test(expected = UsernameExistsException.class)
     public void register_existedUsername_shoudFail() throws RegistrationException {
          String username = "exist";
          String emailAddress = "onair1127@naver.com";
          String password = "snrnsk12@";

          when(repositoryMock.findByUsername(username))
               .thenReturn(new User());

          instance.register(username, emailAddress, password);
     }

     @Test(expected = EmailAddressExistsException.class)
     public void register_existedEmailAddress_shouldFail() throws RegistrationException {
          String username = "onair1127";
          String emailAddress = "exist@naver.com";
          String password = "password";

          when(repositoryMock.findByEmailAddress(emailAddress))
               .thenReturn(new User());

          instance.register(username, emailAddress, password);
     }

     @Test
     public void register_upperCaseEmailAddress_shouldSucceedAndBecomeLowercaer() throws RegistrationException {
          String username = "onair1127";
          String emailAddress = "Onair1127@NavEr.com";
          String password = "password";
          instance.register(username, emailAddress, password);

          User userToSave = User.create(username, emailAddress.toLowerCase(), password);

          verify(repositoryMock).save(userToSave);
     }

     @Test
     public void register_newUser_shouldSucceed() throws RegistrationException {
          String username = "onair1127";
          String emailAddress = "onair1127@naver.com";
          String password = "password";
          String encryptedPassword = "EncryptedPassword";

          User newUser = User.create(username, emailAddress, encryptedPassword);

          // Repository Mock 설정
          // 사용자가 존재하지 않음을 나타내고자 null값 반환
          when(repositoryMock.findByUsername(username)).thenReturn(null);
          when(repositoryMock.findByEmailAddress(emailAddress)).thenReturn(null);
          doNothing().when(repositoryMock).save(newUser);

          // passwordEncryptor Mock 설정
          when(passwordEncryptorMock.encrypt(password))
               .thenReturn("EncryptedPassword");

          User savedUser = instance.register(username, emailAddress, password);

          // 아직 만들기 전이긴 한데, 대충 예측해보자면
          // 위의 비즈니스 로직(register method) 하나의 실행이 아래의 메소드를 모두 실행하는지 검증(verify)하는 테스트 같음.
          InOrder inOrder = inOrder(repositoryMock);
          inOrder.verify(repositoryMock).findByUsername(username);
          inOrder.verify(repositoryMock).findByEmailAddress(emailAddress);
          inOrder.verify(repositoryMock).save(newUser);
          verify(passwordEncryptorMock).encrypt(password);

          assertEquals(encryptedPassword, savedUser.getPassword()
                         ,"Saved User's password should be encrypted.");
     }
}
