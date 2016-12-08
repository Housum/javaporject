package com.hotusm.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class CgLibProxy {

	
	public static UsrLogin getProxyInstace(UsrMethodInterceptor handler){
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(UsrLogin.class);
		enhancer.setCallback(handler);
		return (UsrLogin) enhancer.create();
		
	}
	
	public static void main(String[] args) {
		
		UsrLogin usrLogin = getProxyInstace(new UsrMethodInterceptor());
		usrLogin.login("hotusm");
		usrLogin.logout();
	}
}
