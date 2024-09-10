package com.examplekicklaandwebsite.KickLaand.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("localhost");  // MailDev SMTP server hostname
        mailSender.setPort(1025);  // MailDev SMTP port
        mailSender.setUsername("");  // Leave empty for MailDev
        mailSender.setPassword("");  // Leave empty for MailDev

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");  // No authentication for MailDev
        props.put("mail.smtp.starttls.enable", "false");  // No TLS for MailDev
        props.put("mail.debug", "true");  // Enable debug mode for troubleshooting

        return mailSender;
    }
}


