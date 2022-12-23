package com.pranveraapp.common.presentation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by elion on 22/01/16.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ConfigurationItem {

    public static String ERROR_MESSAGE = "errorMessage";


    String itemName();


    String itemValue();


}
