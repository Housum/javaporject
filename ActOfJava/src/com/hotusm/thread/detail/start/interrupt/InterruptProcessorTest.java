package com.hotusm.thread.detail.start.interrupt;

import com.hotusm.thread.detail.start.Log;

/**
 * ������յ�interrupt�ź�ʱ���̴߳�������״̬��wait()��wait(time)����sleep�����,
 * ��ô�߳̽����׳�InterruptedException�쳣:
 * ��������е�ʱ��  ��ô�ͻ��ж� 
 * 
 * @link http://blog.csdn.net/yinwenjie/article/details/50510915#java
 * 
 * thread.isInterrupted()��Thread.interrupted()������:
 * �����ζ�ŵ�ĳ���̵߳�isInterrupt���Գɹ�����Ϊtrue�������ʹ�ö��󷽷�thread.isInterrupted()��ȡֵ��
 * ��������ȡ���ٴεõ��ķ���ֵ����true�����������ʹ�þ�̬����Thread.interrupted()��ȡֵ,
 * ��ôֻ�е�һ�λ�ȡ�Ľ����true������̵߳�isInterrupt���Խ����ָ���false,
 * ��������ʹ��Thread.interrupted()���û���ʹ��thread.isInterrupted()����,
 * ��ȡ�Ľ������false.
 * 
 *
 */
public class InterruptProcessorTest {
	
	public static void main(String[] args) {
		
		Thread a=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Thread thread=Thread.currentThread();
				while(!thread.isInterrupted()){
					Log.info("�߳�aһֱ��ִ����");
				}
			}
		});
		
		Thread b=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Thread currentThread = Thread.currentThread();
				while(!currentThread.isInterrupted()){
					
					//���һ���̴߳���  wait������sleep��ʱ��   ����interrupt������ʱ��  ��ô���׳�InterruptedException
					synchronized (currentThread) {
						try {
							currentThread.wait();
						} catch (InterruptedException e) {
							//�ں������b.interrupt()��ʱ��  ���׳��쳣
							System.out.println("b�߳������ж��ͺ�,�쳣����   "+currentThread.isInterrupted());
							e.printStackTrace(System.out);
							return;
						}
					}
					
				}
			}
		});
		
		a.start();
		//b.start();
		Log.info("�����߳���������,���ڿ�ʼ�����ж��ź�");
		
		a.interrupt();
		//b.interrupt();
		
		try {
			InterruptProcessorTest.class.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
