package com.hotusm.second;

/**
 * 
 * @author Hotusm
 * 1.����
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
/*1.��������ز��������λ���ķ���*/
class B extends A{
	/*
	 *2.����ѡ��Ҫ��д�ķ�������ʹ��@Overrideע��,���������ض����Ǹ�д
	 *��ʱ��ᱨ��
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
