package com.hotusm.thread.detail.start.notifythread;

import com.hotusm.thread.detail.start.Log;


/**
 * 
 *notify()���ǻ���һ��������Կ�׸�wait()���߳�,����ж�����߳��ڵȴ�,��ô���е�һ���ͻᱻ����(���ʱ���Ǿ���״̬)
 *notifyAll()�������Ǹ�������Щ����  ������������ǻ������б�����Կ�׸�wait()���߳�
 *
 *������ʾ�����Ч��:
 *	�������̶߳�ʹ��ͬһ�������wait()�������й���.Ȼ�������߳�����������Կ�׵�notify()����  ���е�һ���߳��ܹ�
 *  ��ӡ��������  ���������������̲߳�����.�������ǵ���notifyAll()����.���ʱ����ܹ����������̵߳����ݶ�����ӡ������
 *  
 */
public class ParentNotifyThread implements Runnable{
	
	public static final Object MONITOR=new Object();
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			new Thread(new ChildNotifyThread()).start();
		}
		
		//��������ȴ�һ��DEBUG  ���߳������������  ������������߳�ִ����(��Ϊ������һ��ParentNotifyThread.MONITOR.wait()�ǳ���Ҫ)
		synchronized (MONITOR) {
			MONITOR.notify();
			/*
			 * console:
			 * 	�߳� 14�����ɹ�,׼������ȴ�״̬
				�߳� 15�����ɹ�,׼������ȴ�״̬
				�߳� 16�����ɹ�,׼������ȴ�״̬
				(�����debug����ִ��)
				�߳�14������!
			 * */
			//MONITOR.notifyAll();
			/*
			 *console:
			 *	�߳� 14�����ɹ�,׼������ȴ�״̬
				�߳� 16�����ɹ�,׼������ȴ�״̬
				�߳� 15�����ɹ�,׼������ȴ�״̬
				(�����debug����ִ��)
				�߳�15������!
				�߳�14������!
				�߳�16������!
			 * 
			 */
		}
		
		
		//������ǲ��ó����˳�
		synchronized (ParentNotifyThread.class) {
			try {
				ParentNotifyThread.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		new Thread(new ParentNotifyThread()).start();
	}
}
class ChildNotifyThread implements Runnable{

	@Override
	public void run() {
		Thread currentThread=Thread.currentThread();
		long id = currentThread.getId();
		
		Log.info("�߳� "+id+"�����ɹ�,׼������ȴ�״̬");
		
		//���������ParentNotifyThread.MONITOR�����Կ�ס�
		synchronized(ParentNotifyThread.MONITOR){
			try {
				ParentNotifyThread.MONITOR.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Log.error("�߳�"+id+"�������� ");
			}
		}
		Log.info("�߳�"+id+"������!");
	}
}

