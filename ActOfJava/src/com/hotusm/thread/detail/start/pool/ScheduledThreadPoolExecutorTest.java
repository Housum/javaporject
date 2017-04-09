package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 *定时任务调度
 *ScheduledThreadPoolExecutor继承ThreadPoolExecutor
 *除了具有ThreadPoolExecutor的功能以外  还附加了其他的一些方法  这些方法最主要是
 *进行任务的调度
 */
public class ScheduledThreadPoolExecutorTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(5);
		executor.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				Log.info("线程正在执行"+Thread.currentThread().getName());
			}
		}, 3, 3, TimeUnit.SECONDS);
		
	}
}
