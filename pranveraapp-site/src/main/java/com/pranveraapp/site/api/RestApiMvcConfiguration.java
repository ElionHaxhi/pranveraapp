package com.pranveraapp.site.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.pranveraapp.core.web.api.PranveraAppRestApiMvcConfiguration;

/**
 * @author elion
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.pranveraapp.site.api")
public class RestApiMvcConfiguration extends PranveraAppRestApiMvcConfiguration{

}
