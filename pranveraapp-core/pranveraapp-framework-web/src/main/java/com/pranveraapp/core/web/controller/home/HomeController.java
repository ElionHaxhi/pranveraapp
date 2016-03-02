package com.pranveraapp.core.web.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pranveraapp.core.post.service.PostServiceImpl;


@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	PostServiceImpl employeeService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String getGreet(ModelMap uiModel){

		uiModel.addAttribute("name", "Welcome ");
		uiModel.addAttribute("name", " Elion Haxhi");
		
		return "/layout/index";
		
	}
	
}
