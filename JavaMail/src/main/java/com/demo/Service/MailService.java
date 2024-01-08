package com.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;


@Service
public class MailService {

	@Autowired
	private JavaMailSender mailsender;
	
	public void sendSimpleMail(String to,String body,String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("soumikbose650@gmail.com");
		message.setTo(to);
		message.setText(body);
		message.setSubject(subject);

		mailsender.send(message);
	  System.out.println("Mail send");
	}
}
