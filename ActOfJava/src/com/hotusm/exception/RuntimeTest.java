package com.hotusm.exception;

public class RuntimeTest {
	private Excep excep;
	
	public void test(){
		excep.test();
		throw new RuntimeException("");
	}
	
}
interface Excep{
	
	public void test() throws AccountException;
}
