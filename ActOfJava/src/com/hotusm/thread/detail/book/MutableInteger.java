package com.hotusm.thread.detail.book;

/**
 *ʧЧ��ֵ:��ʾ����һ���̶߳��ĵ����ݿ����Ѿ���ʱ�ģ���һ���õ����� 
 *����:A�߳��ڶ�ȡһ��ֵ��ʱ��  B�߳������������ֵ��ʱ��  ��ô���п���
 *A�̶߳������ݾͲ������µ������� ���ʱ��  ���Ǿ���Ҫ����ͬ����  ��֤���ݵ�
 *һ����  ���ܳ���ʧЧ������ 
 */
public class MutableInteger {
	
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
/**
 * 
 *��������еķ�������ͬ��  �����Ͳ��ᵼ�³���ʧЧ������
 *ע�����:����ֻ��set��������ͬ��  getҲ��Ҫ  �������ܽ��б���
 * JDK��Ҳ�кܶ�������ʵ�ֵ�
 * @see java.util.Vector<E>
 * 
 */
class SynchronizedInteger{
	
	private int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}
	
}
