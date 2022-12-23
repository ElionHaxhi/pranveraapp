package com.pranveraapp.common.util;

import com.pranveraapp.common.extension.ExtensionHandler;
import com.pranveraapp.common.extension.ExtensionResultHolder;
import com.pranveraapp.common.extension.ExtensionResultStatusType;

import java.util.Locale;

/**
 * Created by elion on 27/02/16.
 */
public interface PranveraAppMergeResourceExtensionHandler extends ExtensionHandler {

    public ExtensionResultStatusType resolveMessageSource(String code, Locale locale, ExtensionResultHolder<String> result);

}
