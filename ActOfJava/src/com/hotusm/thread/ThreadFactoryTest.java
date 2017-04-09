package com.hotusm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 
 * @author Hotusm
 * @since 2016-03-08
 * ����ExecutorService,�����������Զ�����̳߳�
 * 
 */
public class ThreadFactoryTest {
	
	public static void main(String[] args) {
		ExecutorService es=Executors.newCachedThreadPool(new DeamonFactory());
		for(int i=0;i<5;i++){
			es.execute(new DeamonThread());
		}
		//��ֹ�ڼ��������߳�
		es.shutdown();
		System.out.println("���߳�:"+Thread.currentThread().toString());
	}
}
/**
 * 
 * @author Hotusm
 * ���Ƴ����ɵ��̶߳��Ǻ�̨�߳�
 */
class DeamonFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		//����ֻҪ��������DeamonFactory��Executors
		//��ô���ɳ������̶߳��Ǻ�̨�߳�
		Thread t=new Thread(r);
		//t.setDaemon(true);
		return t;
	}
	
}
//�����Լ���Factory
class DeamonThread implements Runnable{

	@Override
	public void run() {
		while(true){
			System.out.println(Thread.currentThread().toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}






