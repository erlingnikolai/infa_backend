package no.infa.mail;


import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Component
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("thurtimous@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to, String subject, String... templateModel) {

    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

    }

    @Override
    public void sendMessageUsingThymeleafTemplate(String to, String subject, Map<String, Object> templateModel) throws MessagingException {

    }

    @Override
    public void sendMessageUsingFreemarkerTemplate(String to, String subject, Map<String, Object> templateModel) throws IOException, TemplateException, MessagingException {

    }
}