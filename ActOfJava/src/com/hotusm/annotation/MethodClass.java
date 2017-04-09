package com.hotusm.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class MethodClass{

	public void mth1(int i){
		
	}
	
	public void mth2(@Date String date){
		
	}
	public static void main(String[] args) {
		
		  
		Method[] methods = MethodClass.class.getDeclaredMethods();
	    for (Method method:methods) {
	    	
			final Class<?>[] paramTypes = method.getParameterTypes();
		    final Annotation[][] paramAnnotations = method.getParameterAnnotations();
	    	String name = null;
	    	
	    	   int paramCount = paramAnnotations.length;
	    	   
	    	    for (int paramIndex = 0; paramIndex < paramCount; paramIndex++) {
	    	        for (Annotation annotation : paramAnnotations[paramIndex]) {
	    	        		System.out.println(annotation);
	    	          }
	    	    }
		}
	}
}
