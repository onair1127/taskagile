package com.taskagile.domain.model.common.mail;

import org.springframework.stereotype.Component;

@Component
public class DefaultMailManager implements MailManager {

     @Override
     public void send(String emailAddress, String subject, String template, MessageVariable variables) {
          // 이건 뭐 메일 서비스 API 쓴대 ㅎ 몰라 나중에 하겠지

     }

}
