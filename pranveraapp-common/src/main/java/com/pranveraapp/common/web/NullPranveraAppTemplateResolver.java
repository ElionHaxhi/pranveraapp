package com.pranveraapp.common.web;

import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;

/**
 * Created by elion on 28/02/16.
 */
public class NullPranveraAppTemplateResolver implements ITemplateResolver {

    @Override
    public String getName() {
        return "NullPranveraAppTemplateResolver";
    }

    @Override
    public Integer getOrder() {
        return 9999;
    }

    @Override
    public TemplateResolution resolveTemplate(TemplateProcessingParameters templateProcessingParameters) {
        return null;
    }

    @Override
    public void initialize() {

    }
}
