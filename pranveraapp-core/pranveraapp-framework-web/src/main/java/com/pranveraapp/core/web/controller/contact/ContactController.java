package com.pranveraapp.core.web.controller.contact;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pranveraapp.common.ApplicationMailerService;
import com.pranveraapp.common.support.MessageHelper;

import org.springframework.validation.Errors;

@Controller
public class ContactController {
	
	private static final String BASE_MAIL = "elion.haxhi@gmail.com";
	
	private static final String CONTACT_VIEW_NAME = "contact/index";
	
	@Autowired
	private ApplicationMailerService applicationMailerService;

	
	@RequestMapping(value = "/contact/", method = RequestMethod.GET)
	public String contact(Model model) {
		
		ContactForm contactForm = new ContactForm();
		
		model.addAttribute(contactForm);
        return CONTACT_VIEW_NAME;
	}
	
	@RequestMapping(value = "contact", method = RequestMethod.POST)
	public String contact(@Valid @ModelAttribute ContactForm contactForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return CONTACT_VIEW_NAME;
		}
		applicationMailerService.sendMail(BASE_MAIL, contactForm.getSubject(), contactForm.getText()+" mail: " + contactForm.getEmail());
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "contact.success");
		return "redirect:/contact/";
	}
}

