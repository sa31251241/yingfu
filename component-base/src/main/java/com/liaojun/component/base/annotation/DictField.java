package com.liaojun.component.base.annotation;

import java.lang.annotation.*;

/**
 * 字典注解
 * @author peida
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DictField {

    String constantKey() default "";

    String dictGroupCode() default "";

    String collectionKey() default "";

    String keyField() default "id";

    String textField() default "";

    boolean isRefField() default false;

    DICTFIELD_REFTYPE refType() default DICTFIELD_REFTYPE.BEAN;

    public static enum DICTFIELD_REFTYPE {
        BEAN,LIST,MAP
    }
}
