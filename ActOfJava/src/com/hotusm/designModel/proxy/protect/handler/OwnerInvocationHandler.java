package com.hotusm.designModel.proxy.protect.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.hotusm.designModel.proxy.protect.IPersonBean;
import com.hotusm.designModel.proxy.protect.PersonBean;
import com.hotusm.designModel.proxy.protect.ProxyTest;

public class OwnerInvocationHandler implements InvocationHandler{

	private IPersonBean personBean;
	
	public OwnerInvocationHandler(IPersonBean personBean) {
		super();
		this.personBean = personBean;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			
			if(method.getName().startsWith("get")){
				
				return method.invoke(personBean, args);
			}else if(method.getName().equals("setHotOrNotRating")){
				throw new IllegalAccessException("不能给自己设置");
			}else if(method.getName().startsWith("set")){
				return method.invoke(personBean, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public IPersonBean getOwnerProxy(){
		return (IPersonBean) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), personBean.getClass().getInterfaces(), new OwnerInvocationHandler(personBean));
	}
}
