package com.taskagile.web.apis;

import javax.validation.Valid;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.model.user.exception.EmailAddressExistsException;
import com.taskagile.domain.model.user.exception.RegistrationException;
import com.taskagile.domain.model.user.exception.UsernameExistsException;
import com.taskagile.web.results.ApiResult;
import com.taskagile.web.results.Result;
import com.taskagile.web.payload.RegistrationPayload;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationApiController {
     private UserService userService;

     public RegistrationApiController(UserService userService) {
          this.userService = userService;
     }

     @PostMapping("api/registrations")
     public ResponseEntity<ApiResult> register(
          @Valid @RequestBody RegistrationPayload payload
     ) {
          try {
               userService.register(payload.toCommand());
               return Result.created();
          } catch(RegistrationException e) {
               String errorMessage = "Registration Failed";
               if(e instanceof UsernameExistsException) {
                    errorMessage = "Username already exists";
               } else if (e instanceof EmailAddressExistsException) {
                    errorMessage = "Email already exists";
               }
               return Result.failure(errorMessage);
          }
     }
}
