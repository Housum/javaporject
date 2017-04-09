package com.hotusm.runtime;
/**
 * 
 *The API for Runtime 
 *
 */
public class RuntimeProvider {
	
	public void run(){
		int CPU = Runtime.getRuntime().availableProcessors();
		System.out.println("the number of CPU:"+CPU);
	}
	
	public static void main(String[] args) {
		new RuntimeProvider().run();
	}
}
