package com.hotusm.designModel.proxy.protect.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.hotusm.designModel.proxy.protect.IPersonBean;
import com.hotusm.designModel.proxy.protect.ProxyTest;

public class NotOwnerInvocationHandler implements InvocationHandler{

	private IPersonBean personBean;
	
	public NotOwnerInvocationHandler(IPersonBean personBean) {
		super();
		this.personBean = personBean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			
			if(method.getName().startsWith("get")){
				return method.invoke(personBean, args);
			}else if(method.getName().equals("setHotOrNotRating")){
				return method.invoke(personBean, args);
			}else if(method.getName().startsWith("set")){
				throw new IllegalAccessException("不允许给他人设置");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IPersonBean getOwnerProxy(){
		return (IPersonBean) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), personBean.getClass().getInterfaces(), new NotOwnerInvocationHandler(personBean));
	}

}
