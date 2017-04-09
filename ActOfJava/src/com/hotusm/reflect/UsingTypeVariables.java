package com.hotusm.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class UsingTypeVariables {

	@Test
	public void typeVariables(){
		
		Map<String,List<String>> maps=new HashMap<>();
		
		TypeVariable<?>[] typeParameters = maps.getClass().getTypeParameters();
		
		for(TypeVariable tVariable:typeParameters){
			
			Type[] types = tVariable.getBounds();
			
			for(Type type:types){
				
			}
			
		}
		
	}
}
