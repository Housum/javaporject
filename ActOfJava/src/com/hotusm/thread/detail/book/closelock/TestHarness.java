package com.hotusm.thread.detail.book.closelock;

import java.util.concurrent.CountDownLatch;
/**
 * ����  һϵ������ִ����� �Ժ�  
 *
 */
public class TestHarness {
	
	/**
	 * ����n���߳���������ѵ�ʱ��
	 * @param nThreads
	 * @param task
	 * @return
	 */
	public long timeTasks(int nThreads,final Runnable task){
		final CountDownLatch startGate=new CountDownLatch(1);
		final CountDownLatch endGate=new CountDownLatch(nThreads);
		
		for(int i=0;i<nThreads;i++){
			new Thread(){
				public void run() {
					try {
						startGate.await();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					try {
						task.run();
					} finally{
						endGate.countDown();
					}
					
				};
			}.start();
		}
		
		long startTime=System.currentTimeMillis();
		startGate.countDown();
		try {
			endGate.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		long endTime=System.currentTimeMillis();
		
		return endTime-startTime;
	}
	
	public static void main(String[] args) {
		long timeTasks = new TestHarness().timeTasks(20, new Runnable() {
			
			@Override
			public void run() {
				
				for(int i=0,j=0;i<10000;i++){
					j+=i;
				}
			}
		});
		System.out.println(timeTasks);
	}
}
