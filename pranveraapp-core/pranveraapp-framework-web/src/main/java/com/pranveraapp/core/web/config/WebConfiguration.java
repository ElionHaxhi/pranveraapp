package com.pranveraapp.core.web.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.UrlPathHelper;

import com.pranveraapp.common.support.StaticPagePathFinder;

@Configuration
@ControllerAdvice
public class WebConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
    private StaticPagePathFinder staticPagePathFinder;
	
	private static final String RESOURCES_HANDLER = "/resources/";
    private static final String RESOURCES_LOCATION = RESOURCES_HANDLER + "**";
    
    private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";

	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	@Bean(name = { "uih", "viewRenderingHelper" })
    @Scope("request")
    public ViewRenderingHelper viewRenderingHelper() {
        return new ViewRenderingHelper();
    }
	
	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        /*try {
	            for (StaticPagePathFinder.PagePaths paths : staticPagePathFinder.findPaths()) {
	                String urlPath = paths.getUrlPath();
	                registry.addViewController(urlPath).setViewName("pages" + paths.getFilePath());
	                if (!urlPath.isEmpty()) {
	                    registry.addViewController(urlPath + "/").setViewName("pages" + paths.getFilePath());
	                }
	            }

	        } catch (IOException e) {
	            throw new RuntimeException("Unable to locate static pages: " + e.getMessage(), e);
	        }*/
	    }

	 
	 
	 
	/* @Override
	 public void addInterceptors(InterceptorRegistry registry) {
	 registry.addInterceptor(localeChangeInterceptor());
	 }*/
	 
	 


/*	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("elLocaleCode");
		return localeChangeInterceptor;
	}*/
	

	@Bean
    public JavaMailSenderImpl mailSender() throws KeyManagementException, NoSuchAlgorithmException {



        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.debug", "true");



        JavaMailSenderImpl mailSender = new org.springframework.mail.javamail.JavaMailSenderImpl();
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("elion.haxhi@gmail.com");
        mailSender.setPassword("rvzvihefmxtbxnos");
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }
	
	static class ViewRenderingHelper {

        private final UrlPathHelper urlPathHelper = new UrlPathHelper();

        private HttpServletRequest request;


        @Autowired
        public void setRequest(HttpServletRequest request) {
            this.request = request;
        }

        public String navClass(String active, String current) {
            if (active.equals(current)) {
                return "navbar-link active";
            } else {
                return "navbar-link";
            }
        }

        public String blogClass(String active, String current) {
            if (active.equals(current)) {
                return "blog-category active";
            } else {
                return "blog-category";
            }
        }

        public String path() {
            return urlPathHelper.getPathWithinApplication(request);
        }
    }
	
}
