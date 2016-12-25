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
 *���ע���������������̬�Ĺ������Ե�
 *pojoָ�����ĸ���
 *valueָ������Щ����
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface IgnoreProperty {
	/**
	 * ָ����
	 */
	Class<?> pojo();
	
	/**
	 *ָ�����������Щ������Ҫ���˵� 
	 */
	String[] value();
}
