package com.econception.employemanagement.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class MailService {

    //https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/integration.html#mail
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${econ.ems.from-name}")
    private String fromName;

    @Value(("${econ.ems.from-email}"))
    private String fromEmail;


    public void sendEmail(String to, String subject, String text) {

        try {
            log.info(String.format("Sending email to %s with subject %s", to, subject));
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(fromName + "<" + fromEmail + ">");
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(text);

            javaMailSender.send(msg);
        } catch (Exception e) {
            log.error("Send Email error", e);
        }

    }

    public void sendHtmlEmail(String to, String subject, String content) {
        // Prepare message using a Spring helper
        log.info(String.format("Sending HTML email to %s with subject %s", to, subject));
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(fromName + "<" + fromEmail + ">");
            message.setSubject(subject);
            message.setText(content, true);
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            log.error("Send HTML Email error", e);
        }
    }

}
