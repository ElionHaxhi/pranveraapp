package com.pranveraapp.common.security;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by elion on 07/02/16.
 */
public class PranveraAppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

     protected static final String SESSION_ATTR = "SFP-ActiveID";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        String targetUrl = request.getParameter(getTargetUrlParameter());
        if (StringUtils.isNotBlank(targetUrl) && targetUrl.contains(":")) {
            getRedirectStrategy().sendRedirect(request, response, getDefaultTargetUrl());
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
