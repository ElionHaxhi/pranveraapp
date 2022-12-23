package com.pranveraapp.core.web.resolver;

import com.pranveraapp.common.extension.ExtensionManager;
import org.springframework.stereotype.Service;

/**
 * Created by elion on 28/02/16.
 */
@Service("elDatabaseResourceResolverExtensionManager")
public class DatabaseResourceResolverExtensionManager extends ExtensionManager<DatabaseResourceResolverExtensionHandler> {

    public DatabaseResourceResolverExtensionManager() {
        super(DatabaseResourceResolverExtensionHandler.class);
    }

    /**
     * By default, this manager will allow other handlers to process the method when a handler returns
     * HANDLED.
     */
    @Override
    public boolean continueOnHandled() {
        return false;
    }
}
