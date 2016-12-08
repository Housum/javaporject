package com.hotusm.thread.detail.start.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import com.hotusm.thread.detail.start.Log;

public class TestCountDownLatch {
	
	public static void main(String[] args) {
		
		final AtomicInteger index=new AtomicInteger(0);
		final CountDownLatch cdl=new CountDownLatch(5);
		for(int i=0;i<5;i++){
			
		//	new Thread(new RunnImpl(cdl,index)).start();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Log.info("线程"+index.getAndIncrement()+"");
					try {
						synchronized (this) {
							System.out.println(this);
							this.wait(2000);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					cdl.countDown();
				}
			}).start();
		}
		
		try {
			cdl.await();
			Log.info("线程全部执行完了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class RunnImpl implements Runnable{
	private CountDownLatch cdl;
	private AtomicInteger index;
	public RunnImpl(CountDownLatch cdl,AtomicInteger index){
		this.cdl=cdl;
		this.index=index;
	}
	@Override
	public void run() {
		Log.info("线程"+index.getAndIncrement()+"");
		try {
			//如果将这个监听
			//synchronized (this) {
				//System.out.println(this);  有可能多个线程同时调用这个方法  因为现在有多个线程执行  
				this.wait(2000);
			//}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cdl.countDown();
	}
}
