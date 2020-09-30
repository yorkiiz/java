package com.matrix.cim.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableField {
    public String value() default "默认值...";

    public String brmTableName() default "";


    public String pptTableName() default "";

}
