package com.pranveraapp.core.web.resolver;

import com.pranveraapp.common.extension.ExtensionResultHolder;
import com.pranveraapp.common.extension.ExtensionResultStatusType;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.resourceresolver.IResourceResolver;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * Created by elion on 28/02/16.
 */
@Service("elDatabaseResourceResolver")
public class DatabaseResourceResolver implements IResourceResolver {
    @Override
    public String getName() {
        return "EL_DATABASE";
    }

    @Resource(name = "elDatabaseResourceResolverExtensionManager")
    protected DatabaseResourceResolverExtensionManager extensionManager;

    @Override
    public InputStream getResourceAsStream(TemplateProcessingParameters params, String resourceName) {
        ExtensionResultHolder erh = new ExtensionResultHolder();
        ExtensionResultStatusType result = extensionManager.getProxy().resolveResource(erh, params, resourceName);
        if (result ==  ExtensionResultStatusType.HANDLED) {
            return (InputStream) erh.getContextMap().get(DatabaseResourceResolverExtensionHandler.IS_KEY);
        }
        return null;
    }
}
