package com.hotusm.thread.detail.start.pool.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * Executors ��һ�������̵߳Ĺ�����
 * ��Щ�����ഴ�����̳߳�ʹ�õ�BlockingQueue����LinkedBlockingQueue
 * �����߳̿������޵����(�����ܹ�ͬʱִ�е��߳��ǲ�ͬ�ĸ���)
 */
public class ToolTest {

	@Test
	public void testNewFixedThreadPoolMethod(){
		/**
		 * �����������ָ���������̳߳� 
		 * ������java.util.concurrent.ThreadPoolExecutor
		 * corePoolSize��maximumPoolSize����ͬ�� keepAliveTime����
		 * ��˼��������ָ���Ĳ����Ƕ�� ��ô�̳߳ؾ��Ƕ��  �̳߳��еĶ��Ǻ����߳�
		 * 
		 */
		ExecutorService executors = Executors.newFixedThreadPool(10);
		for(int i=0;i<15;i++){
			executors.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.info("�߳���������");
				}
			});
		}
		synchronized(this){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �����������ָ���������̳߳� 
	 * ������java.util.concurrent.ThreadPoolExecutor
	 * corePoolSize��maximumPoolSize������Ϊ1  ˵���̳߳���ֻ�ܹ�ִ��һ������
	 * 
	 */
	@Test
	public void testNewSingleThreadExecutor(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		/**
		 * ������������߳�����  ��Ȼ�������е�����wait()��������Դ�����˳�ȥ   
		 * ��������һ������ȴ���ܹ�ִ��  ֻ�ܵ�һ������������Ժ�  ���ܹ���ʼ����һ������ 
		 * ��ͺܺõ�˵�������������  �̳߳���ֻ�ᴴ��һ������ 
		 * ����ж���Ļ�  ��ô�ͻ�һֱ�ȴ�ǰ������������
		 */
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				synchronized (this) {
					try {
						this.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Log.info("�߳�1����");
			}
		});
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				synchronized (this) {
					try {
						this.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Log.info("�߳�2����");
			}
		});
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �������͵�corePoolSizeΪ0,maximumPoolSizeΪInteger.MAX_VALUE
	 * ����ʱ������Ϊ60S .���������һ�������̳߳�
	 * 
	 */
	@Test
	public void testNewCachedThreadPool(){
		final AtomicInteger index=new AtomicInteger(1);
		
		ExecutorService executors = Executors.newCachedThreadPool();
		for(int i=0;i<50;i++){
			executors.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.info("����"+index.getAndIncrement()+"�߳�");
				}
			});
		}
		
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
