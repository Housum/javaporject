package com.hotusm.reflect;

import java.lang.reflect.Method;

public class FieldTest {
	
	public static void main(String[] args) {
//		try {
//			 Field[] dfs = User.class.getDeclaredFields();
//			 for(Field f:dfs){
//				 System.out.println(f.getName());
//			 }
//			 
//			//System.out.println(name);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		boolean hasMethod = hasMethod("name");
		System.out.println(hasMethod);
		try {
			User.class.getDeclaredMethod("getName");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}	
	}
	
	public static boolean hasMethod(String column){
		try {
			//System.out.println("get"+column.substring(0, 1).toUpperCase()+column.substring(1));
			Method method = User.class.getMethod("get"+column.substring(0, 1).toUpperCase()+column.substring(1));
			System.out.println(method.getName());
		}catch (Exception e) {
			return false;
		}
		return true;
	}

}
