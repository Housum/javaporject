package com.hotusm.jackson.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 *
 *这个注解就是我们用来动态的过滤属性的
 *pojo指定是哪个类
 *value指定是那些属性
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IgnoreProperty {
	/**
	 * 指定类
	 */
	Class<?> pojo();
	
	/**
	 *指定上面的类那些属性需要过滤的 
	 */
	String[] value();
}
