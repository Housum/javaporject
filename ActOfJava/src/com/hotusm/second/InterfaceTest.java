package com.hotusm.second;

/**
 * 
 * @author Hotusm
 * 1.�ӿڲ���
 */
public interface InterfaceTest {
	/*1.�ڲ��ӿ�
	 * */
	interface H{
		public void say1();
	}
	public void say();
}
/*2.ֱ�Ӽ̳нӿ�,����Ҫʵ���ڲ��Ľӿ�
 * 
 * */
class Test implements InterfaceTest{

	@Override
	public void say() {
		
	}
}
/*3.ʵ���ڲ��Ľӿ�ʹ��,ֻ��Ҫʹ���޶���ʵ�ֽӿڼ���
 * 
 * */
class Test1 implements InterfaceTest.H{

	@Override
	public void say1() {
		
	}
	
}
