package com.hotusm.thread.detail.start;

public class MyDefindRunnable implements Runnable{

	@Override
	public void run() {
		   //����ȡ��ǰ�̵߳ģɣ�
        long threadId = Thread.currentThread().getId();
        Log.info("�̣߳�" + threadId + "������һЩ���飬Ȼ������ˡ�");
	}
	
	public static void main(String[] args) {
		new Thread(new MyDefindRunnable()).start();
	}
}
