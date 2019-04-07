package com.remango.blog.aop;

import java.lang.annotation.*;

/**
 * @author zhengli
 * @since 2019/03/07
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    String value() default "";
}
