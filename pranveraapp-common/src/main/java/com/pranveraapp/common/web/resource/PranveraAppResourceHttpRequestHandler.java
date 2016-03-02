package com.pranveraapp.common.web.resource;

import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by elion on 04/02/16.
 */
public class PranveraAppResourceHttpRequestHandler extends ResourceHttpRequestHandler {

    @Resource(name = "elPranveraAppContextUtil")
    protected PranveraAppContextUtil pranveraAppContextUtil;

     @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            pranveraAppContextUtil.establishThinRequestContext();
            super.handleRequest(request, response);
        } finally {
            pranveraAppContextUtil.clearThinRequestContext();
        }
    }
}
