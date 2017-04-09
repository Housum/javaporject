package com.hotusm.thread;

/**
 * 
 * @author Hotusm
 * @since 2016-03-13
 * join���÷� 
 * 
 * ���һ���߳�a��b�߳���ʹ��a.join(),��ôb�̻߳�һֱ
 * �ȴ�a�߳�ִ�����֮��,�Ż����ִ��b�̵߳�����
 * 
 * 
 */
public class JoinTest {
	
	public static void main(String[] args) {
		Sleeper s=new Sleeper();
		Join j=new Join(s);
	}
}

class Sleeper extends Thread{
	
	public Sleeper() {
		start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("��������...");
	}
}
class Join extends Thread{
	
	private Sleeper sleeper;

	public Join(Sleeper sleeper) {
		super();
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void run() {
		/**
		 * �߳�Sleeper���������߳�Join�е�����
		 * join����,��ôJoin�߳̾ͻ�ȴ�Sleeper�߳�ִ�����
		 * �Ժ󣬲Ż�ִ��.
		 *  
		 */
		try {
			this.sleeper.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Join����");
	}
	
}
