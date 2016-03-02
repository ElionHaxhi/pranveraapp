package com.pranveraapp.core.web.controller.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by elion on 28/02/16.
 */
@Controller("elPostController")
public class PostController extends PranveraAppPostController {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
       return  super.handleRequest(request, response);
    }
}
