package com.hotusm.second;

/**
 * 
 * @author Hotusm
 * 1.重载
 */

public class OverrideTest {

	public static void main(String[] args) {
		B b=new B();
		b.say(1);
		b.say("1");
	}
}

class A{
	
	void say(String a){
		System.out.println("A");
	}
}
/*1.子类的重载并不会屏蔽基类的方法*/
class B extends A{
	/*
	 *2.对于选择要覆写的方法可以使用@Override注解,当出现重载而不是覆写
	 *的时候会报错
	 * */
	@Override
	void say(String a) {
		// TODO Auto-generated method stub
		super.say(a);
	}

	void say(Integer b){
		System.out.println("B");
	}
}
