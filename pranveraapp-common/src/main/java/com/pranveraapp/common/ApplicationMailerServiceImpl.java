package com.pranveraapp.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Service("mailService")
public class ApplicationMailerServiceImpl implements ApplicationMailerService {
	
	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * @Autowired private SimpleMailMessage preConfiguredMessage;
	 */
	/**
	 * This method will send compose and send the message
	 */
	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
//		javaMailSender.send(message); //decoment this to run the email service correctly
	}

	public void sendPostePerShiko(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}

	/**
	 * This method will send a pre-configured message
	 */
	// public void sendPreConfiguredMail(String message)
	// {
	// SimpleMailMessage mailMessage = new
	// SimpleMailMessage(preConfiguredMessage);
	// mailMessage.setText(message);
	// mailSender.send(mailMessage);
	// }
}
