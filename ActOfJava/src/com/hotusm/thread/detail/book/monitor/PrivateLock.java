package com.hotusm.thread.detail.book.monitor;

import com.hotusm.thread.detail.book.threadclose.Person;

/**
 * JAVA ������ģʽ
 * ��ѭJAVA����ģʽ�Ķ����Ѷ���Ŀɱ����ȫ�����з�װ  ���ɶ���������������б���
 */
public class PrivateLock {

	private final Object mutex=new Object();
	Person person;
	void someMethod(){
		synchronized (mutex) {
			//�������޸�person��״̬���̰߳�ȫ��
		}
	}
}
