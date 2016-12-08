package com.hotusm.thread.detail.start.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

import com.hotusm.thread.detail.start.Log;
/**
 * 
 * CyclicBarrier���÷���CountDownLatch���÷����� 
 * ���ǲ�ͬ�����ڲ�����ʽ�ĵ���countDown()����
 * ,await()������һֱ�����������������̲߳Ż᲻����
 *  ����Ŀ��Կ�ͼƬ
 */	
public class TestCyclicBarrier {
	
	public static void main(String[] args) {
		final CyclicBarrier cb=new CyclicBarrier(3);
		final AtomicInteger index=new AtomicInteger(0);
		//�����������Ϊ2�Ļ� ��ô�̻߳�һֱ���� ����ִ��  ��Ϊ�߳���������3��
		for(int i=0;i<2/*3*/;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						cb.await();
						Log.info("�߳�"+index.getAndIncrement()+"��������");
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
