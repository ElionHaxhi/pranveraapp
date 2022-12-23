package com.pranveraapp.common.web;

import com.pranveraapp.common.classloader.release.ThreadLocalManager;
import com.pranveraapp.common.locale.domain.Locale;
import com.pranveraapp.common.util.ELRequestUtils;
import com.pranveraapp.common.web.exception.HaltFilterChainException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by elion on 06/02/16.
 */
@Component("elRequestProcessor")
public class PranveraAppRequestProcessor extends AbstractPranveraAppWebRequestProcessor {

    protected final Log LOG = LogFactory.getLog(getClass());


    public static String REPROCESS_PARAM_NAME = "REPROCESS_EL_REQUEST";

    @Resource(name = "elLocaleResolver")
    PranveraAppLocaleResolver localeResolver;

    @Override
    public void process(WebRequest request){
        PranveraAppRequestContext prc = new PranveraAppRequestContext();
        prc.setWebRequest(request);
        PranveraAppRequestContext.setPranveraappRequestContext(prc);

        Locale locale = localeResolver.resolveLocale(request);
        prc.setLocale(locale);

         // When a user elects to switch his sandbox, we want to invalidate the current session. We'll then redirect the
        // user to the current URL so that the configured filters trigger again appropriately.
        Boolean reprocessRequest = (Boolean) request.getAttribute(PranveraAppRequestProcessor.REPROCESS_PARAM_NAME, WebRequest.SCOPE_REQUEST);
        if (reprocessRequest != null && reprocessRequest) {
            LOG.debug("Reprocessing request");
            if (request instanceof ServletWebRequest) {
                HttpServletRequest hsr = ((ServletWebRequest) request).getRequest();

                clearBroadleafSessionAttrs(request);

                StringBuffer url = hsr.getRequestURL();
                if (hsr.getQueryString() != null) {
                    url.append('?').append(hsr.getQueryString());
                }
                try {
                    ((ServletWebRequest) request).getResponse().sendRedirect(url.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                throw new HaltFilterChainException("Reprocess required, redirecting user");
            }
        }

    }

    protected void clearBroadleafSessionAttrs(WebRequest request) {
        if (ELRequestUtils.isOKtoUseSession(request)) {
            request.removeAttribute(PranveraAppLocaleResolverImpl.LOCALE_VAR, WebRequest.SCOPE_GLOBAL_SESSION);
        }
    }

    @Override
    public void postProcess(WebRequest request){
        ThreadLocalManager.remove();
    }
}
