package com.hotusm.test.rpc.test;

import org.junit.Test;

import com.hotusm.proxy.cglib.UsrLogin;
import com.hotusm.test.rpc.methodhandler.MethodHandler;
import com.hotusm.test.rpc.register.Resgiter;

import net.sf.cglib.proxy.Enhancer;

public class RpcTest {

	@Test
	public void registry(){
		
		BaseServer baseServer=new TemplateServer();
		
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(BaseServer.class);
		enhancer.setCallback(new MethodHandler());
		
		Resgiter register=new Resgiter();
		register.register(baseServer);
		
		
		BaseServer baseServer1 =(BaseServer) enhancer.create();
		baseServer1.sayHello("123");
		
	}
}
