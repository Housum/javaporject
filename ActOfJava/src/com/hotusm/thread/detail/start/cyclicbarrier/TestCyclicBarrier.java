package com.hotusm.thread.detail.start.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import com.hotusm.thread.detail.start.Log;
/**
 * 
 * CyclicBarrier的用法和CountDownLatch的用法相似 
 * 但是不同点在于不用显式的调用countDown()方法
 * ,await()方法会一直堵塞到满足数量的线程才会不堵塞
 *  具体的可以看图片
 */	
public class TestCyclicBarrier {
	
	public static void main(String[] args) {
		final CyclicBarrier cb=new CyclicBarrier(3);
		final AtomicInteger index=new AtomicInteger(0);
		//如果这里设置为2的话 那么线程会一直堵塞 不就执行  因为线程数量少于3个
		for(int i=0;i<2/*3*/;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						cb.await();
						Log.info("线程"+index.getAndIncrement()+"正在运行");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					
				}
			}).start();;
		}
	}
}
