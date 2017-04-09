package com.hotusm.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 
 * @author Hotusm
 * @since 2106-03-19
 * Timer类的使用
 */
public class TimerTest {
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask(){
			//5秒种之后执行
			
			@Override
			public void run() {
				System.out.println("----");
				
			}}, 5000);
	}

}
