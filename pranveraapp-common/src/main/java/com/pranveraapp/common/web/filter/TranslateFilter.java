package com.pranveraapp.common.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by elion on 27/02/16.
 */
@Component("elTranslateFilter")
public class TranslateFilter extends GenericFilterBean{

    @Resource(name = "elTranslateRequestProcessor")
    protected TranslateRequestProcessor translateRequestProcessor;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            translateRequestProcessor.process(new ServletWebRequest((HttpServletRequest) request, (HttpServletResponse) response));
            filterChain.doFilter(request, response);
        } finally {
            translateRequestProcessor.postProcess(new ServletWebRequest((HttpServletRequest) request, (HttpServletResponse) response));
        }
    }
}
