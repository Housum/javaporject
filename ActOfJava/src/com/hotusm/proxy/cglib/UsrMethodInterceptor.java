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
			System.out.println("�û�׼����½");
			result = proxy.invokeSuper(obj, args);
			System.out.println("��½�ɹ�  ��������: "+(int)result+"��");
		}else{
			System.out.println("�û��˳�...");
			result = proxy.invokeSuper(obj, args);
		}
		return result;
	}
	

}
