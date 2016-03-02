package com.pranveraapp.common.presentation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by elion on 22/01/16.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AdminPresentationClass {


    PopulateToOneFieldsEnum populateToOneFields() default PopulateToOneFieldsEnum.NOT_SPECIFIED;


    String friendlyName() default "";


    String ceilingDisplayEntity() default "";


    boolean excludeFromPolymorphism() default false;
}
