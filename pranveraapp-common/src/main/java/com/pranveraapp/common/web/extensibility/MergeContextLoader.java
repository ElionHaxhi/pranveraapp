package com.pranveraapp.common.web.extensibility;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import com.pranveraapp.common.classloader.release.ThreadLocalManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Created by elion on 25/01/16.
 */
public class MergeContextLoader  extends ContextLoaderListener{

    public static final String PATCH_LOCATION_PARAM = "patchConfigLocation";
    public static final String SHUTDOWN_HOOK_BEAN = "shutdownHookBean";
    public static final String SHUTDOWN_HOOK_METHOD = "shutdownHookMethod";

    /*@Override
    @Deprecated
    protected WebApplicationContext createWebApplicationContext(ServletContext servletContext, ApplicationContext parent) throws BeansException {
        MergeXmlWebApplicationContext wac = new MergeXmlWebApplicationContext();
        wac.setParent(parent);
        wac.setServletContext(servletContext);
        wac.setConfigLocation(servletContext.getInitParameter(ContextLoader.CONFIG_LOCATION_PARAM));
        wac.setPatchLocation(servletContext.getInitParameter(PATCH_LOCATION_PARAM));
        wac.setShutdownBean(servletContext.getInitParameter(SHUTDOWN_HOOK_BEAN));
        wac.setShutdownMethod(servletContext.getInitParameter(SHUTDOWN_HOOK_METHOD));
        customizeContext(servletContext, wac);
        wac.refresh();

        return wac;
    }
*/
    @Override
    protected WebApplicationContext createWebApplicationContext(ServletContext servletContext) throws BeansException {
        MergeXmlWebApplicationContext wac = new MergeXmlWebApplicationContext();
        wac.setServletContext(servletContext);
        wac.setConfigLocation(servletContext.getInitParameter(ContextLoader.CONFIG_LOCATION_PARAM));
        wac.setPatchLocation(servletContext.getInitParameter(PATCH_LOCATION_PARAM));
        wac.setShutdownBean(servletContext.getInitParameter(SHUTDOWN_HOOK_BEAN));
        wac.setShutdownMethod(servletContext.getInitParameter(SHUTDOWN_HOOK_METHOD));
        customizeContext(servletContext, wac);
        //NOTE: in Spring 3.1, refresh gets called automatically. All that is required is to return the context back to Spring
        //wac.refresh();

        return wac;
    }


    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        ThreadLocalManager.remove();
    }
}
