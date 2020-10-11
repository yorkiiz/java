package com.matrix.cim.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface PKField {
    public String value() default "默认值...";
}
