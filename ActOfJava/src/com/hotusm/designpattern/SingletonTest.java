package com.hotusm.designpattern;

/**
 * 
 * @author Hotusm
 * ���ģʽ->����ģʽ
 */
public class SingletonTest {
	
	private static SingletonTest singletonTest=new SingletonTest();
	/*1.�����캯����Ϊprivate��,�����ͻ��˳���Ա�Ͳ��ܹ�ֱ�ӵĴ�����ʵ����*/
	private SingletonTest(){}
	
	/*2.ͨ����̬���������Է�����ʵ��*/
	public static SingletonTest instance(){
		return singletonTest;
	}
	public void sayHello(){
		System.out.println("�������ģʽ");
	}
	public static void main(String[] args) {
		SingletonTest sTest=SingletonTest.instance();
		sTest.sayHello();
	}
}
