package com.taskagile.web.payload;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationPayloadTests {
     private Validator validator;

     @Before
     public void setup() {
          ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
          validator = factory.getValidator();
     }

     @Test
     public void validate_blankPayload_shouldFail() {
          RegistrationPayload payload = new RegistrationPayload();
          Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
          assertEquals(3, violations.size());
     }

     @Test
     public void validate_payloadWithInvalidEmail_shouldFail() {
          RegistrationPayload payload = new RegistrationPayload();
          payload.setUsername("username");
          payload.setEmailAddress("BadEmail");
          payload.setPassword("password");
          Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
          assertEquals(1, violations.size());
     }

     @Test
     public void validate_payloadWithLongerThan100Characters_shouldFail() {
          // RandomStringUtils로 랜덤 문자열 생성(최대: 64)
          int maxLocalParthLength = 64;
          String localPart = RandomStringUtils.random(maxLocalParthLength, true, true);
          int usedLength = maxLocalParthLength + "@".length() + ".com".length();
          String domain = RandomStringUtils.random(101 - usedLength, true, true);

          RegistrationPayload payload = new RegistrationPayload();
          payload.setUsername("username");
          payload.setEmailAddress(localPart + domain);
          payload.setPassword("password");

          Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
          assertEquals(1, violations.size());
     }

     @Test
     public void validate_payloadWithUsernameShorterThan2_shouldFail() {
          RegistrationPayload payload = new RegistrationPayload();

          String usernameTooShort = RandomStringUtils.random(1);
          payload.setUsername(usernameTooShort);
          payload.setEmailAddress("emailAddress@naver.com");
          payload.setPassword("password");

          Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
          assertEquals(1, violations.size());
     }

     @Test
     public void validate_payloadWithUsernameLongerThan50_shouldFail() {
          RegistrationPayload payload = new RegistrationPayload();

          String usernameTooLong = RandomStringUtils.random(51);
          payload.setUsername(usernameTooLong);
          payload.setEmailAddress("emailAddress@naver.com");
          payload.setPassword("password");

          Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
          assertEquals(1, violations.size());
     }

     @Test
     public void validate_payloadWithPasswordShorterThan6_shouldFail() {
          RegistrationPayload payload = new RegistrationPayload();

          String passwordTooShort = RandomStringUtils.random(5);
          payload.setUsername("username");
          payload.setEmailAddress("emailAddress@naver.com");
          payload.setPassword(passwordTooShort);

          Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
          assertEquals(1, violations.size());
     }

     @Test
     public void validate_payloadWithPasswordLongerThan30_shouldFail() {
          RegistrationPayload payload = new RegistrationPayload();

          String passwordTooLong = RandomStringUtils.random(31);
          payload.setUsername("username");
          payload.setEmailAddress("emailAddress@naver.com");
          payload.setPassword(passwordTooLong);

          Set<ConstraintViolation<RegistrationPayload>> violations = validator.validate(payload);
          assertEquals(1, violations.size());
     }

}
