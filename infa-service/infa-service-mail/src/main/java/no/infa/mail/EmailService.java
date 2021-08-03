package no.infa.mail;

import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Service
public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        String... templateModel);

    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);

    void sendMessageUsingThymeleafTemplate(String to,
                                           String subject,
                                           Map<String, Object> templateModel)
            throws IOException, MessagingException;

    void sendMessageUsingFreemarkerTemplate(String to,
                                            String subject,
                                            Map<String, Object> templateModel)
            throws IOException, TemplateException, MessagingException, TemplateException;
}