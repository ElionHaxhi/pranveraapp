package com.pranveraapp.common.extensibility.context.merge;

import org.springframework.core.Ordered;

/**
 * Created by elion on 14/02/16.
 */
public class LateStateMergeBeanPostProcessor extends AbstractMergeBeanPostProcessor implements Ordered {

    protected int order = Integer.MAX_VALUE;

    /**
     * The regular ordering for this post processor in relation to other post processors. The default
     * value is Integer.MAX_VALUE.
     */
    @Override
    public int getOrder() {
        return order;
    }

    /**
     * The regular ordering for this post processor in relation to other post processors. The default
     * value is Integer.MAX_VALUE.
     *
     * @param order the regular ordering
     */
    public void setOrder(int order) {
        this.order = order;
    }

}
