package com.pranveraapp.core.web.resolver;

import com.pranveraapp.common.exception.AbstractExtensionHandler;
import com.pranveraapp.common.extension.ExtensionResultHolder;
import com.pranveraapp.common.extension.ExtensionResultStatusType;
import org.thymeleaf.TemplateProcessingParameters;

/**
 * Created by elion on 28/02/16.
 */
public class AbstractDatabaseResourceResolverExtensionManager extends AbstractExtensionHandler
implements DatabaseResourceResolverExtensionHandler {

    public ExtensionResultStatusType resolveResource(ExtensionResultHolder erh,
            TemplateProcessingParameters params, String resourceName) {
        return ExtensionResultStatusType.NOT_HANDLED;
    }
}
