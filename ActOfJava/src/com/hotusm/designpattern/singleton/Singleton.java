package com.hotusm.designpattern.singleton;

import java.util.concurrent.TimeUnit;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月10日   <br/>
 * @description  对于很多情况  可能系统中只需要一个实例 
 * 这个时候就可以使用单件模式
 */
public class Singleton {
	
	private static Singleton PRODUCER;
	
	//将构造函数设置为私有的  这样其他的类都不能够访问  只能够本类进行访问
	private Singleton(){
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static Singleton getInstance(){
		
		//如果不进行同步的话  那么在多线程中会出现多个线程同时的进入到null中  会出现重复创建的问题
		if(PRODUCER==null)
			synchronized (Singleton.class) {
				PRODUCER=new Singleton();
			}
		
		return PRODUCER;
	}
	
	public static void main(String[] args) {
		
		Singleton.getInstance();
		System.out.println("---------");
		Singleton.getInstance();
	}
}
