package com.matrix.cim.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PaField {

    public String value() default "默认值...";

}
