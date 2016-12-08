package com.hotusm.thread.detail.start.sleep;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 *
 *1.sleep()并不会释放资源(如果占用了钥匙的话  那么是不会释放)
 *但是wait()方法是会释放资源
 *2.在sleep()堵塞的时候  调用interrupt()也会抛出InterruptedException异常
 */
public class SleepThread implements Runnable{
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new SleepThread());
		Thread t2 = new Thread(new SleepThread());
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		
		synchronized(SleepThread.class){
			Log.info(Thread.currentThread().getName()+"线程正在执行...");
			try {
				Thread.sleep(3000);
				
				//但是wait会将资源让出来
				//SleepThread.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
