package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 * 
 *@link http://blog.csdn.net/yinwenjie/article/details/50522458
 *
 */
public class PoolThread {
	
	public static void main(String[] args) {
	     /*
         * corePoolSize�����Ĵ�С���̳߳س�ʼ����ʱ�򣬾ͻ�����ô��
         * maximumPoolSize���̳߳�����߳���
         * keepAliveTime�������ǰ�̳߳����߳�������corePoolSize��
         * ������̣߳��ڵȴ�keepAliveTimeʱ��������û���µ��߳�����ָ�ɸ��������ͻᱻ����
         * 
         * unit���ȴ�ʱ��keepAliveTime�ĵ�λ
         * 
         * workQueue���ȴ����С��������������Ǳ��Ľ��ص���ܵ�����
         * */
		ThreadPoolExecutor executor=new ThreadPoolExecutor(5, 10,1,TimeUnit.MINUTES, new SynchronousQueue<Runnable>());
		
		//�����Ǵ�����10���߳�  ��Ϊ����������corePoolSizeΪ5  ������ִ�����Ժ�  ����keepAliveTimeʱ���Ժ�  
		//��ô��Щ�Ǻ����̻߳ᱻ�Ƴ��̳߳���
		for(int i=0;i<10;i++){
			executor.submit(new TestThread(i));
		}
		
		//���ú����߳��ڵȴ�keepAliveTime֮��  ���л��� ��������仰�Ժ�  DEBUG��ʱ�� ����
		//�Ϳ��Է����ڵȴ�һ��ʱ���Ժ�   �߳�ȫ��������
		//executor.allowCoreThreadTimeOut(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//ͨ��ʵ������:������û�з��κε�������ȥ�Ļ�  ��ô�ǲ��ᴴ���κε������
		//prestartAllCoreThreads������������̳߳ش���������û�н��յ��κ����������£����д�������corePoolSize����ֵ���߳�����
		//executor.prestartAllCoreThreads();
		
	}
}
class TestThread implements Runnable{
	
	private int index;
	
	public TestThread(int index){
		this.index=index;
	}

	@Override
	public void run() {
		Thread thread=Thread.currentThread();
		Log.info("�߳�:"+thread.getId()+"�е�����("+this.index+"��������");
		//�����ʾ���ǵ�ǰ���߳�
		synchronized(thread){
			try {
				thread.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Log.info("�߳�:"+thread.getId()+"�е�����("+this.index+"ִ�����");
	}
}
