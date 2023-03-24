package com.pranveraapp.common;

public interface ApplicationMailerService {
	public void sendMail(String to, String subject, String body);
	public void sendPostePerShiko(String to, String subject, String body);
}
