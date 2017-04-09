package com.hotusm.thread.detail.book.executejob;

import java.util.Scanner;

import com.hotusm.thread.detail.start.Log;

public class DaemonTest {
	
	public static void main(String[] args) {
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				Log.info("JVM exit!!!");
			}
		});
		
		Thread t=new Thread(){
			@Override
			public void run() {
				//虽然这个线程一直在执行  但是只要没有了线程的时候   那么就会推出
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Log.info("thread number:"+Thread.currentThread().getName());
				}
			}
		};
		//设置为守护线程  必须在start之前调用
		t.setDaemon(true);
		t.start();
		
		Scanner scanner=new Scanner(System.in);
		scanner.next();
	}
}
