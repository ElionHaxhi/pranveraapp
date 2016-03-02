package com.pranveraapp.common.web;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import javax.annotation.Resource;

/**
 * Created by elion on 06/02/16.
 */
public class PranveraAppRequestInterceptor implements WebRequestInterceptor {


    @Resource(name = "elRequestProcessor")
    protected PranveraAppRequestProcessor requestProcessor;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        requestProcessor.process(request);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        //unimplemented
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        requestProcessor.postProcess(request);
    }
}
