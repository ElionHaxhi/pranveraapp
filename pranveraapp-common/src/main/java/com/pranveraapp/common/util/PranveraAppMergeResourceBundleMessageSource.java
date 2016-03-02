package com.pranveraapp.common.util;

import com.pranveraapp.common.extension.ExtensionResultHolder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * Created by elion on 27/02/16.
 */
public class PranveraAppMergeResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {


    @Resource(name = "elPranveraAppMergeResourceExtensionManager")
    protected PranveraAppMergeResourceExtensionManager extensionManager;

    @Override
    public void setBasenames(String... basenames) {
        CollectionUtils.reverseArray(basenames);
        super.setBasenames(basenames);
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        ExtensionResultHolder<String> messageHolder = new ExtensionResultHolder<String>();
        extensionManager.getProxy().resolveMessageSource(code, locale, messageHolder);
        if (StringUtils.isNotBlank(messageHolder.getResult())) {
            return createMessageFormat(messageHolder.getResult(), locale);
        }

        return super.resolveCode(code, locale);
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        ExtensionResultHolder<String> messageHolder = new ExtensionResultHolder<String>();
        extensionManager.getProxy().resolveMessageSource(code, locale, messageHolder);
        if (StringUtils.isNotBlank(messageHolder.getResult())) {
            return messageHolder.getResult();
        }

        return super.resolveCodeWithoutArguments(code, locale);
    }
}
