package com.pranveraapp.site.config;

import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by elion on 18/12/16.
 * serve per chiamare profili diversi cioe se local o development o prod
 * qui puoi chiamare un servizioe e ottenere il tipo di profilo dal db
 */
public class ActiveProfileConfiguration implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {


        String profilo = sce.getServletContext().getInitParameter("spring.profiles.active");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME,profilo);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
