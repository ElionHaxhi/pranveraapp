package com.pranveraapp.core.web.resolver;

import com.pranveraapp.common.extension.ExtensionHandler;
import com.pranveraapp.common.extension.ExtensionResultHolder;
import com.pranveraapp.common.extension.ExtensionResultStatusType;
import org.thymeleaf.TemplateProcessingParameters;

/**
 * Created by elion on 28/02/16.
 */
public interface DatabaseResourceResolverExtensionHandler extends ExtensionHandler {

    public static final String IS_KEY = "IS_KEY";

    /**
     * If this method returns any of the handled conditions in {@link ExtensionResultStatusType},
     * the value keyed by {@link DatabaseResourceResolverExtensionHandler.IS_KEY} in the
     * {@link ExtensionResultHolder}'s context map will be an {@link InputStream} of the resolved resource's
     * contents.
     *
     * @param erh
     * @param params
     * @param resourceName
     * @return whether or not a resource was resolved
     */
    public ExtensionResultStatusType resolveResource(ExtensionResultHolder erh,
            TemplateProcessingParameters params, String resourceName);

}
