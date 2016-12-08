package com.hotusm.second;

/**
 * 
 * @author Hotusm
 * 1.接口测试
 */
public interface InterfaceTest {
	/*1.内部接口
	 * */
	interface H{
		public void say1();
	}
	public void say();
}
/*2.直接继承接口,不需要实现内部的接口
 * 
 * */
class Test implements InterfaceTest{

	@Override
	public void say() {
		
	}
}
/*3.实现内部的接口使用,只需要使用限定名实现接口即可
 * 
 * */
class Test1 implements InterfaceTest.H{

	@Override
	public void say1() {
		
	}
	
}
