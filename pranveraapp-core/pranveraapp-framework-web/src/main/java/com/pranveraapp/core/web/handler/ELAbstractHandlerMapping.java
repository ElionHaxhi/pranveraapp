package com.pranveraapp.core.web.handler;

import org.springframework.web.servlet.handler.AbstractHandlerMapping;

/**
 * Created by elion on 03/02/16.
 * Adds some convenience methods to the Spring AbstractHandlerMapping  for
 * EL specific HandlerMappings.
 *
 */

public abstract class ELAbstractHandlerMapping extends AbstractHandlerMapping{

    protected String controllerName;

    /**
     * This handler mapping does not provide a default handler.
     * This mathos has been coded to always return null.
     * @return
     */
    @Override
    public Object getDefaultHandler(){
        return null;
    }

    /**
     * Returns the controllerName if set or "elPageController" by default.
     * @return
     */
    public String getControllerName() {
        return controllerName;
    }

    /**
     * Sets the name of the bean to use the Handler. Typically the name of a controller bean.
     * @param controllerName
     */
    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }
}
