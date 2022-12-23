package com.pranveraapp.common.web.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by elion on 20/01/16.
 */
public abstract class PranveraAppAbstractController {

    protected<T> void addDeepLink(ModelAndView model){}
    protected String getContextPath(HttpServletRequest request){
        return "ciao";
    }
}
