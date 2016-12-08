package com.hotusm.annctation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * string ���͵��ֶ�
 * @author Hotusm
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
	int value() default 255;	//�ֶεĳ��� Ĭ����255
	String name() default"";
	//Ĭ������µ�ֵ ����@Constraints�����õ���Щֵ
	Constraints constraints() default @Constraints;
}
