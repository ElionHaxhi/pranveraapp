package com.pranveraapp.core.web.controller.work;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/work/")
public class WorkController {

	@RequestMapping(method = RequestMethod.GET)
	public String getWorks(){
		return "/work/index";
	}
	@RequestMapping(value="/docup/", method = RequestMethod.GET)
	public String getDokUp(){
		return "/work/docup/index";
	}
	@RequestMapping(value="/efe/", method = RequestMethod.GET)
	public String getEfe(){
		return "/work/efe/index";
	}
	@RequestMapping(value="/ebe/", method = RequestMethod.GET)
	public String getEbe(){
		return "/work/ebe/index";
	}
	@RequestMapping(value="/pvpro/", method = RequestMethod.GET)
	public String getPvpro(){
		return "/work/pvpro/index";
	}
	@RequestMapping(value="/admin/", method = RequestMethod.GET)
	public String getAdmin(){
		return "/work/admin/index";
	}
	
}
