package com.hotusm.thread.detail.book;

/**
 * ÿ��JAVA���󶼿�������һ��ͬ������
 * ��Щ����Ϊ������(intrinsic)�������(Monitor Lock)
 * ����һ�ֻ����� 
 */
public class InnerLock {
	
	public synchronized void method1(){
		
	}
	public synchronized void method2(){
		method1();
		System.out.println("");
	}
	
	public static void main(String[] args) {
		/**
		 * ������ʾ���������һ������
		 * ��Ϊ���ַ�ʽ��JAVA����ά������һ����
		 * �������һ������ �������������� ����
		 * �����ͬһ���̵߳Ļ�  ����������Ļ� ��ô
		 * �Ϳ��Զ�εĽ��� ÿ�ν����ʱ�� ������¼�ϼ�
		 * 1 �˳�һ���������ʱ�� ��һ �������ܱ������
		 * ����һ�������� (����ͨ��DEBUG���鿴���ַ�ʽ)
		 * 
		 */
		new InnerLock().method2();
	}
}
