package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 *��ʱ�������
 *ScheduledThreadPoolExecutor�̳�ThreadPoolExecutor
 *���˾���ThreadPoolExecutor�Ĺ�������  ��������������һЩ����  ��Щ��������Ҫ��
 *��������ĵ���
 */
public class ScheduledThreadPoolExecutorTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(5);
		executor.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				Log.info("�߳�����ִ��"+Thread.currentThread().getName());
			}
		}, 3, 3, TimeUnit.SECONDS);
		
	}
}
