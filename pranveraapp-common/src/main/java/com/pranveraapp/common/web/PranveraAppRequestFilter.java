package com.pranveraapp.common.web;

import com.pranveraapp.common.web.exception.SiteNotFoundException;
import com.pranveraapp.common.web.exception.HaltFilterChainException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by elion on 06/02/16.
 */
@Component("elRequestFilter")
public class PranveraAppRequestFilter extends OncePerRequestFilter {


    private final Log LOG = LogFactory.getLog(getClass());

    @Resource(name = "elRequestProcessor")
    protected PranveraAppRequestProcessor requestProcessor;

    @Override
    public void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain)throws IOException,ServletException{


        try{
            requestProcessor.process(new ServletWebRequest(request,response));
            filterChain.doFilter(request,response);
        }
        catch (HaltFilterChainException e){
            return;
        }
        catch (SiteNotFoundException e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        finally {
            requestProcessor.postProcess(new ServletWebRequest(request,response));
        }

    }

}
