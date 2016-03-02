package com.pranveraapp.common.web.filter;

import com.pranveraapp.common.i18n.service.TranslateConsiderationContext;
import com.pranveraapp.common.i18n.service.TranslateService;
import com.pranveraapp.common.util.ELSystemProperty;
import com.pranveraapp.common.web.AbstractPranveraAppWebRequestProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;

/**
 * Created by elion on 27/02/16.
 */
@Component("elTranslateRequestProcessor")
public class TranslateRequestProcessor extends AbstractPranveraAppWebRequestProcessor {

    @Resource(name = "elTranslateService")
    protected TranslateService translateService;

    /**
     *
     * @return
     */
    protected boolean getTranslationEnabled() {
        return true;
    }

    @Override
    public void process(WebRequest request) {
        TranslateConsiderationContext.setTranslateConsiderationContext(getTranslationEnabled());
        TranslateConsiderationContext.setTranslateService(translateService);
    }
}
