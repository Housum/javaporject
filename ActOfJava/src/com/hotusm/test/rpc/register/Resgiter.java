package com.hotusm.test.rpc.register;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hotusm.thread.detail.start.Log;

public class Resgiter {

	public Map<Class<?>,Method[]> repository=new ConcurrentHashMap<>();
	
	public void register(Object object){

		Log.info("×¢²á·þÎñ:"+object);
		repository.put(doFindClass(object), doFindMethod(object));
	}
	
	private Method[] doFindMethod(Object object){
		
		return object.getClass().getDeclaredMethods();
	}
	
	private Class<?> doFindClass(Object object){
		
		return object.getClass();
	}
	
}
