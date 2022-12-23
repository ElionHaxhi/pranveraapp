package com.pranveraapp.common.extensibility.jpa.copy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by elion on 22/01/16.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DirectCopyTransform {


    DirectCopyTransformMember[] value();
}
