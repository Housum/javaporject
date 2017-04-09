package com.hotusm.proxy.cglib;

import java.util.Random;

public class UsrLogin {
	
	private static ThreadLocal<String> tl=new ThreadLocal<String>();
	
	public int login(String name){
		Random random = new Random();
		int time = random.nextInt(10)+1;
		System.out.println(name+"ÕýÔÚµÇÂ½...");
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tl.set(name);
		return time;
	}
	
	public void logout(){
		String name = tl.get();
		System.out.println(name+"ÍË³ö");
		tl.remove();
	}
}
