package com.hotusm.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UsrMethodInterceptor implements MethodInterceptor{

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Object result=null;
		if(args!=null&&args.length>0){
			System.out.println("用户准备登陆");
			result = proxy.invokeSuper(obj, args);
			System.out.println("登陆成功  共花费了: "+(int)result+"秒");
		}else{
			System.out.println("用户退出...");
			result = proxy.invokeSuper(obj, args);
		}
		return result;
	}
	

}
