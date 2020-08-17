package com.taskagile.domain.model.common.mail;

public interface MailManager {
     void send(String emailAddress, String subject, String template, MessageVariable variables);
}
