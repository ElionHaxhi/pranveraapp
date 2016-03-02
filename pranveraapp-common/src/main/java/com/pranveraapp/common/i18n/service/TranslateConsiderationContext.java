package com.pranveraapp.common.i18n.service;

import com.pranveraapp.common.classloader.release.ThreadLocalManager;

/**
 * Created by elion on 25/02/16.
 */
public class TranslateConsiderationContext {
    private static final ThreadLocal<TranslateConsiderationContext> translateConsiderationContext = ThreadLocalManager.createThreadLocal(TranslateConsiderationContext.class);

    public static boolean hasTranslation() {
        return getTranslateConsiderationContext() != null && getTranslateConsiderationContext() && getTranslateService() != null;
    }

    public static Boolean getTranslateConsiderationContext() {
        Boolean val = TranslateConsiderationContext.translateConsiderationContext.get().enabled;
        return val == null ? false : val;
    }

    public static void setTranslateConsiderationContext(Boolean isEnabled) {
        TranslateConsiderationContext.translateConsiderationContext.get().enabled = isEnabled;
    }

    public static TranslateService getTranslateService() {
        return TranslateConsiderationContext.translateConsiderationContext.get().service;
    }

    public static void setTranslateService(TranslateService translationService) {
        TranslateConsiderationContext.translateConsiderationContext.get().service = translationService;
    }

    protected Boolean enabled = true;
    protected TranslateService service;
}
