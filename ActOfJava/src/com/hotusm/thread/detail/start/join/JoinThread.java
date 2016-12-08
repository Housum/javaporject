package com.hotusm.thread.detail.start.join;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 * 
 *这个是用来测试join()方法的  
 *具体的就是A线程调用B线程的join()方法  那么A线程会等到B线程执行完以后（死亡状态）
 *A才会继续执行
 *
 *  XXX:调用join方法的线程，如果接收到interrupt信号，也会抛出InterruptedException异常。
 *  
 */
public class JoinThread implements Runnable{
	
	public static void main(String[] args) {
		
		Log.info("===========启动main线程==============");
		Thread thread=Thread.currentThread();
		long id = thread.getId();
		
		Thread thread2 = new Thread(new JoinThread());

		thread2.start();
		try {
			//在这里执行JoinThread线程的join()方法  那么main方法就
			//会一直等待JoinThread执行完成(死亡状态)
			Log.info("======准备调用JoinThread.join()方法=====");
			//
			//thread2.join();
			
			//join(millis)：调用线程等待millis毫秒后，无论目标线程执行是否完成，当前调用线程都会继续执行；
			//thread2.join(2*1000);
			
			/**
			 * join(millis, nanos)：调用线程等待 millis毫秒 + nanos 纳秒 时间后,
			 * 无论无论目标线程执行是否完成，当前调用线程都会继续执行；
			 * 实际上这个join方法的描述并不准确：第二个参数nanos只是一个参考值（修正值），
			 * 且只有大于等于500000时，第二个参数才会起作用（纳秒是一秒的十亿分之一）
			 */
			thread2.join(2*1000, 500001);
			Log.info("============mian继续执行=============");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void run() {
		
		Thread thread=Thread.currentThread();
		long id = thread.getId();
		Log.info("=====线程"+id+"启动成功，准备进入等待状态(5秒)====");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Log.info("===========线程"+id+"执行完成============");
	}
}
