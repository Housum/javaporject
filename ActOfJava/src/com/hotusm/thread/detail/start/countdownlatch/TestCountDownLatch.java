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
					Log.info("�߳�"+index.getAndIncrement()+"");
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
			Log.info("�߳�ȫ��ִ������");
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
		Log.info("�߳�"+index.getAndIncrement()+"");
		try {
			//������������
			//synchronized (this) {
				//System.out.println(this);  �п��ܶ���߳�ͬʱ�����������  ��Ϊ�����ж���߳�ִ��  
				this.wait(2000);
			//}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cdl.countDown();
	}
}
