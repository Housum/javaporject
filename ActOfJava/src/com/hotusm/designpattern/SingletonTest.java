package com.hotusm.designpattern;

/**
 * 
 * @author Hotusm
 * 设计模式->单例模式
 */
public class SingletonTest {
	
	private static SingletonTest singletonTest=new SingletonTest();
	/*1.将构造函数置为private的,这样客户端程序员就不能够直接的创建类实例了*/
	private SingletonTest(){}
	
	/*2.通过静态方法的特性返回类实体*/
	public static SingletonTest instance(){
		return singletonTest;
	}
	public void sayHello(){
		System.out.println("单例设计模式");
	}
	public static void main(String[] args) {
		SingletonTest sTest=SingletonTest.instance();
		sTest.sayHello();
	}
}
