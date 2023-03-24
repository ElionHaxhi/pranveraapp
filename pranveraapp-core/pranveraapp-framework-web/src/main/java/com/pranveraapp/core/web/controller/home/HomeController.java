package com.pranveraapp.core.web.controller.home;

import com.pranveraapp.common.ApplicationMailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pranveraapp.core.post.service.PostServiceImpl;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class HomeController {

	public static final String ELION_HAXHI_GMAIL_COM = "elion.haxhi@gmail.com";
	@Autowired
	PostServiceImpl employeeService;


	@Autowired
	private ApplicationMailerService applicationMailerService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String getGreet(ModelMap uiModel, HttpServletRequest httpServletRequest){

		uiModel.addAttribute("name", "Welcome ");
		uiModel.addAttribute("name", " Elion Haxhi");
		String visite = "Visits on http://pranveraapp.com";
		String ipAddress = httpServletRequest.getRemoteAddr();

//		applicationMailerService.sendPostePerShiko(ELION_HAXHI_GMAIL_COM,visite, ipAddress);
		
		return "/layout/index";
		
	}
	
}
