package com.hotusm.thread.detail.book;


public class UnsafeSequence {
	private int value;
	
	/**
	 * ���������һ�������Ĳ��� ������ʵ
	 * ����һ���̲߳���ȫ�Ĳ���  
	 * 
	 * ������� ���ǳ�Ϊ��̬����
	 * 
	 */
//	public int getValue(){
//		return value++;
//	}
	
	/**
	 * ����Ļ�����  ���Ǽ���synchronizedʵ�ֶ���ķ���ͬ��������(����߳�ʹ��ͬһ�������ʱ�� ���÷���ʱ��ֵ��һ���̵߳���)
	 */
	public synchronized int getValue(){
		return value++;
	}
	
	
}

