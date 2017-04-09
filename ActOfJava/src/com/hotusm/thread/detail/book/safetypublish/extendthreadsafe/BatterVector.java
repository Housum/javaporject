package com.hotusm.thread.detail.book.safetypublish.extendthreadsafe;

import java.util.Vector;
/**
 * �����е��̰߳�ȫ������ӹ���
 * ����ʵ�ֵ���һ�����������  ��ô����ӵĹ���  �������  ��ô�����
 */
public class BatterVector<E> extends Vector<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * �͸��ౣ����һ����ͬ������  ������û�������.�����п��ܻ���������
	 * ��֪�������ͬ�����Ե���� 
	 * @param e
	 * @return
	 */
	public synchronized boolean putIfAbsent(E e){
		boolean absent=!contains(e);
		if(absent){
			add(e);
		}
		return absent;
	}
}
