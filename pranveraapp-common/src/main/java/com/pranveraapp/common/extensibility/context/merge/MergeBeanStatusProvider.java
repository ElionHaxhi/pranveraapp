package com.pranveraapp.common.extensibility.context.merge;

import org.springframework.context.ApplicationContext;

/**
 * Created by elion on 14/02/16.
 */
public interface MergeBeanStatusProvider {

    /**
     * Typically used by the {@link AbstractMergeBeanPostProcessor} class to determine whether or not certain
     * lists should be processed or if they can be safely ignored.
     *
     * @param bean
     * @param beanName
     * @param appCtx
     * @return whether or not processing should be triggered
     */
    public boolean isProcessingEnabled(Object bean, String beanName, ApplicationContext appCtx);

}
