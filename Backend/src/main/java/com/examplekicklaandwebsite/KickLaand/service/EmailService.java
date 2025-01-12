package com.examplekicklaandwebsite.KickLaand.service;

import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.examplekicklaandwebsite.KickLaand.dto.MailBody;

@Service
public class EmailService {

	private final JavaMailSender javaMailSender;
	
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendSimpleMessage(MailBody mailBody) throws MessagingException {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mailBody.to());
			message.setFrom("weatherappproj@gmail.com");
			message.setSubject(mailBody.subject());
			message.setText(mailBody.text());

			javaMailSender.send(message);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
