package com.taskagile.web.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taskagile.domain.application.UserService;
import com.taskagile.domain.model.user.exception.UsernameExistsException;
import com.taskagile.utils.JsonUtils;
import com.taskagile.web.apis.RegistrationApiController;
import com.taskagile.web.payload.RegistrationPayload;


@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationApiController.class)
public class RegistrationApiControllerTest {

     @Autowired
     private MockMvc mvc;

     @MockBean
     private UserService serviceMock;

     @Test
     public void register_blankPayload_shouldFailAndReturn400() throws Exception {
          mvc.perform(post("/api/registrations"))
               .andExpect(status().is(400));
     }

     @Test
     public void register_existedUsername_shouldFailAndReturn400() throws Exception {
          RegistrationPayload payload = new RegistrationPayload();
          payload.setUsername("exist");
          payload.setEmailAddress("emailAddress@naver.com");
          payload.setPassword("password");

          doThrow(UsernameExistsException.class)
               .when(serviceMock)
               .register(payload.toCommand());

          mvc.perform(
               post("/api/registrations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.toJson(payload)))
               .andExpect(status().is(400))
               .andExpect(jsonPath("$.message").value("Username already exists"));
     }

}
