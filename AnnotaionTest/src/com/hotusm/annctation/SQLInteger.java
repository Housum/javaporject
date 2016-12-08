package com.hotusm.annctation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *int ÐÍµÄ×Ö¶Î 
 * @author Hotusm
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
	
	String name() default "";
	Constraints constraints() default @Constraints;
}
