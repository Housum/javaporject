package com.hotusm.test.rpc.methodhandler;

import java.lang.reflect.Method;

import com.hotusm.thread.detail.start.Log;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MethodHandler implements MethodInterceptor{

	@Override
	public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy arg3) throws Throwable {
		
		Log.info("客户端调用:"+method);
		return null;
	}
}
