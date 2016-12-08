package com.hotusm.test.rpc.test;

public class TemplateServer implements BaseServer{

	@Override
	public void sayHello(String userName) {

		System.out.println("hello "+userName);
	}

}
