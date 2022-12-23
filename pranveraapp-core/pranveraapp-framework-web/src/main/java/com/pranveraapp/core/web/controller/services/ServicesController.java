package com.pranveraapp.core.web.controller.services;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/services/")
public class ServicesController {

	@RequestMapping(method = RequestMethod.GET)
	public String getServices(){
		return "/services/index";
	}
}