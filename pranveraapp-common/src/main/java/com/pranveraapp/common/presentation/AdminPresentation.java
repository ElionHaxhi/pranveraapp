package com.pranveraapp.common.presentation;

import com.pranveraapp.common.presentation.client.SupportedFieldType;
import com.pranveraapp.common.presentation.client.VisibilityEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by elion on 22/01/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AdminPresentation {


    String friendlyName() default "";


    String securityLevel() default "";


    int order() default 99999;


    int gridOrder() default 9999;


    VisibilityEnum visibility() default VisibilityEnum.VISIBLE_ALL;

    SupportedFieldType fieldType() default SupportedFieldType.UNKNOWN;

    String group() default "General";

    int groupOrder() default 99999;


    boolean groupCollapsed() default false;


    String tab() default "General";


    int tabOrder() default 100;


    boolean largeEntry() default false;

    boolean prominent() default false;

    String columnWidth() default "*";

    String broadleafEnumeration() default "";

    boolean readOnly() default false;

    ValidationConfiguration[] validationConfigurations() default {};

    RequiredOverride requiredOverride() default RequiredOverride.IGNORED;

    boolean excluded() default false;

    String tooltip() default "";

    String helpText() default "";


    String hint() default "";


    String showIfProperty() default "";

    String currencyCodeField() default "";

    String ruleIdentifier() default "";

    boolean translatable() default false;

    String defaultValue() default "";
}
