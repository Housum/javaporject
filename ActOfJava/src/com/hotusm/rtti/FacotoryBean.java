package com.hotusm.rtti;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public interface FacotoryBean<T> {
	
	static class Test{
		public static void main(String[] args) {
			
			Class<?> clazz=ClassTest.class;
			//可以返回类  方法  字段的修饰符 getModifiers
			boolean p = Modifier.isPublic(clazz.getModifiers());
			System.out.println(p);
			try {
				Method method = clazz.getDeclaredMethod("testModifier");
				System.out.println(Modifier.isPrivate(method.getModifiers()));
				Field field = clazz.getDeclaredField("memo");
				System.out.println(Modifier.isPrivate(field.getModifiers()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
