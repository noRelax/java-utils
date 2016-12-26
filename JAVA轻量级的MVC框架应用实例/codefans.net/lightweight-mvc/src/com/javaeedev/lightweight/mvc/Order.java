package com.javaeedev.lightweight.mvc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify orders. Lower value has a higher priority.
 * 
 * @author Xuefeng
 * @finishing www.codefans.net
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Order {

    int value() default 0;

}
