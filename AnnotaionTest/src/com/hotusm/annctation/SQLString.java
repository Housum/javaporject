package com.hotusm.annctation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * string 类型的字段
 * @author Hotusm
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
	int value() default 255;	//字段的长度 默认是255
	String name() default"";
	//默认情况下的值 就是@Constraints中设置的那些值
	Constraints constraints() default @Constraints;
}
