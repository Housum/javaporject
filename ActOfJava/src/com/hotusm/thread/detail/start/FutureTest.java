package com.hotusm.thread.detail.start;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 *@java.util.concurrent.Future<V>
 *��ʾ�첽����Ľ��  ֻ���ڼ��������ʱ����ܹ����ؽ��  ��Ȼ�Ļ�  �ͻ����
 *�������ǿ���ͨ��cancel��������ȡ��
 */
public class FutureTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor=new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES,  new SynchronousQueue<Runnable>());
		Future<String> future = executor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Log.info("�߳�"+Thread.currentThread().getName()+"��������");
				//������������һ�仰  ��ô	Log.info("result: "+future.get());
				//���ֻ���ڵȴ������Ժ���ܹ�����ֵ  ��Ϊ����û��ɵĻ�  ��ô�ͻ�һֱ���� ֱ���������
				Thread.sleep(2000);
				return "the result";
			}
			
		});
		
		Log.info("�߳�"+Thread.currentThread().getName()+"��������");
		//ֻ�ܵ��߳̽���������Ϣ
		Log.info("future: "+future.get());
		
		Future<String> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
				Log.info("�߳�"+Thread.currentThread().getName()+"��������");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "ok");
		
		//ֻ�ܵ��߳�����Ժ�  �ŻὫ�ڶ��������еĽ������
		Log.info("future1: "+future1.get());
		
	}
}
