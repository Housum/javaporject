package com.hotusm.thread.detail.start.sleep;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 *
 *1.sleep()�������ͷ���Դ(���ռ����Կ�׵Ļ�  ��ô�ǲ����ͷ�)
 *����wait()�����ǻ��ͷ���Դ
 *2.��sleep()������ʱ��  ����interrupt()Ҳ���׳�InterruptedException�쳣
 */
public class SleepThread implements Runnable{
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new SleepThread());
		Thread t2 = new Thread(new SleepThread());
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		
		synchronized(SleepThread.class){
			Log.info(Thread.currentThread().getName()+"�߳�����ִ��...");
			try {
				Thread.sleep(3000);
				
				//����wait�Ὣ��Դ�ó���
				//SleepThread.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
