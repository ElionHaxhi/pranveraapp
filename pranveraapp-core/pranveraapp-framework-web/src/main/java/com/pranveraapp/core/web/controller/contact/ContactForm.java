package com.pranveraapp.core.web.controller.contact;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class ContactForm {

	private static final String NOT_BLANK_SUBJECT_MESSAGE = "{notBlank.subject.message}";
	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String NOT_BLANK_NAME_MESSAGE = "{notBlank.name.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";

	@NotBlank(message = ContactForm.NOT_BLANK_NAME_MESSAGE)
	private String name;

	@NotBlank(message = ContactForm.NOT_BLANK_MESSAGE)
	@Email(message = ContactForm.EMAIL_MESSAGE)
	private String email;

	@NotBlank(message = ContactForm.NOT_BLANK_SUBJECT_MESSAGE)
	private String subject;

	@NotBlank(message = ContactForm.NOT_BLANK_MESSAGE)
	private String text;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String message) {
		this.text = message;
	}

}
