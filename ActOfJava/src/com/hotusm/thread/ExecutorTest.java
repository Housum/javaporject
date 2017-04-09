package com.hotusm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java�����Ļ���
 * 1.ExecutorService����ִ���߳�,������ͨ��Thread.start()���ַ�ʽ����.
 * ͨ�����ַ�ʽ���Ը��õĹ����߳�
 * 2.ͨ��Executors�ľ�̬������ȡ���̹߳�����
 * ��Executors.newCachedThreadPool();Ϊÿһ���߳�
 * ������һ���߳�
 * ��Executors.newFixedThreadPool(nThreads);����ִ���������߳���
 * ��Executors.newSingleThreadExecutor();�����������̣߳�����кܶ�
 * ������,��ô��Щ������������ִ����һ��������ִ����һ������
 * ��Executors.newScheduledThreadPool(corePoolSize)����һ��
 * �����б�͢�����
 * 
 * @author Hotusm
 * @since 2016-03-07
 */
public class ExecutorTest {

	public static void main(String[] args) {
		ExecutorService es=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			es.execute(new RunnableImpl());
		}
		//ֹͣ���Է�ֹ�µ������ύ��ExecutorService
		es.shutdown();
	}
}
// the implement of Runnable
class RunnableImpl implements Runnable{

	int i;
	@Override
	public void run() {
		while(i<20){
			
			System.out.println(i++);
			/**
			 * ��ʾ���Ǹ����̹߳�����:��ǰ�߳��Ѿ�ִ�к�������Ҫ�Ĳ���
			 * ����ִ���������߳���
			 * 
			 */
			Thread.yield();

		}
	}
	
}
