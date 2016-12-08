package com.hotusm.designModel.singleton;

import java.util.concurrent.TimeUnit;

public class Singleton2 {

	private static Singleton2 PRODUCER;
	
	private Singleton2(){
		
		System.out.println("start init");
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Singleton2 getInstance(){
		if(PRODUCER==null){
			PRODUCER=Singleton2Holder.PRODUCER;
		}
		return PRODUCER;
	}
	//嵌套类的加载是在使用到的时候
	private static class Singleton2Holder{
		
		public static Singleton2 PRODUCER;
		
		static{
			PRODUCER=new Singleton2();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		Singleton2.getInstance();
		System.out.println("--------------");
		Singleton2.getInstance();
	}
}
