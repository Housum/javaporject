package com.hotusm.thread.detail.start.join;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 * 
 *�������������join()������  
 *����ľ���A�̵߳���B�̵߳�join()����  ��ôA�̻߳�ȵ�B�߳�ִ�����Ժ�����״̬��
 *A�Ż����ִ��
 *
 *  XXX:����join�������̣߳�������յ�interrupt�źţ�Ҳ���׳�InterruptedException�쳣��
 *  
 */
public class JoinThread implements Runnable{
	
	public static void main(String[] args) {
		
		Log.info("===========����main�߳�==============");
		Thread thread=Thread.currentThread();
		long id = thread.getId();
		
		Thread thread2 = new Thread(new JoinThread());

		thread2.start();
		try {
			//������ִ��JoinThread�̵߳�join()����  ��ômain������
			//��һֱ�ȴ�JoinThreadִ�����(����״̬)
			Log.info("======׼������JoinThread.join()����=====");
			//
			//thread2.join();
			
			//join(millis)�������̵߳ȴ�millis���������Ŀ���߳�ִ���Ƿ���ɣ���ǰ�����̶߳������ִ�У�
			//thread2.join(2*1000);
			
			/**
			 * join(millis, nanos)�������̵߳ȴ� millis���� + nanos ���� ʱ���,
			 * ��������Ŀ���߳�ִ���Ƿ���ɣ���ǰ�����̶߳������ִ�У�
			 * ʵ�������join��������������׼ȷ���ڶ�������nanosֻ��һ���ο�ֵ������ֵ����
			 * ��ֻ�д��ڵ���500000ʱ���ڶ��������Ż������ã�������һ���ʮ�ڷ�֮һ��
			 */
			thread2.join(2*1000, 500001);
			Log.info("============mian����ִ��=============");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void run() {
		
		Thread thread=Thread.currentThread();
		long id = thread.getId();
		Log.info("=====�߳�"+id+"�����ɹ���׼������ȴ�״̬(5��)====");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Log.info("===========�߳�"+id+"ִ�����============");
	}
}
