package com.hotusm.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * 
 * @author Hotusm
 * @since 2106-03-19
 * Timer���ʹ��
 */
public class TimerTest {
	
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask(){
			//5����֮��ִ��
			
			@Override
			public void run() {
				System.out.println("----");
				
			}}, 5000);
	}

}
