package com.hotusm.thread.detail.book;
/**
 * 1.�̵߳Ŀɼ���Ҳ��һ���Ƚ����ص�����
 *  ������������ͻ�����ɼ��Ե�����  ��Ȼ��main������������ready=true
 *  �� ReaderThread ���߳��ܹ�����ִ����ȥ  ���Ǻ�������� ������������  �ͺ�����
 *  readyһֱ����false ��Ϊ�����̸߳ı��ֵ ����ReaderThread�߳���˵�ǲ��ɼ���
 * 2.��Ȼ���ݵĿɼ���������  ���߳���û��ͬ��������� ���ܻ�õ�һ��ʧЧֵ,����������֮ǰ��
 * ĳ���̵߳�ֵ ������һ������� �������Ͱ�ȫ��
 *  
 */
public class NoVisibility {
	
	private static boolean ready;
	private static int number=0;
	
	private static class ReaderThread extends Thread{
		
		/**
		 * yield() �ͷŵ�ǰ���߳� (��������Դ��ʱ�� ��ô�ͻ����ִ������߳�)
		 * ������������þ���Ϊ�˷�ֹһ���߳�ռ��̫�õ�ʱ��
		 */
		@Override
		public void run() {
				/**
				 * 
				 */
				while(!ready)Thread.yield();
				System.out.println(number);
		}
		
	} 
	public static void main(String[] args) {
//		ReaderThread t1 = new ReaderThread();
//		t1.start();
		new ReaderThread().start();
		synchronized (NoVisibility.class) {
			try {
				NoVisibility.class.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number=42;
		ready=true;
		//һֱ�ȴ�t1ִ�����߳�
//		try {
//			t1.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}