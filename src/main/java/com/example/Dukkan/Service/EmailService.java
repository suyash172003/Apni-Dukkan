package com.example.Dukkan.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	JavaMailSender sendEmail;
	
	@Value("$(spring.mail.username)")
	private String fromEmailId;
	
	
	public void sendEmailTo(String recipient,String subject,String body) {
		SimpleMailMessage obj=new SimpleMailMessage();
		obj.setFrom(fromEmailId);
		obj.setTo(recipient);
		obj.setSubject(subject);
		obj.setText(body);
		sendEmail.send(obj);		
	}
}
