package com.hotusm.thread.detail.book.safecontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.junit.Test;

/**
 * 
 *��ʾһЩ�̰߳�ȫ��������
 *	Hashtable synchronizedList ConcurrentMap Vertor
 *	CopyOnWriteArrayList  CopyOnWriteArraySet
 *	����: BlockingQueue  ConcurrentLinkedQueue
 *	ֻҪ�Ƿ�����Щ�̰߳�ȫ�������  ��ô���ݶ����������߳���˵Ҳ��
 *  �ɼ���
 *  2.ͨ��Collections�кܶ�ķ��� �ܹ���һ�������ת��Ϊͬ��������
 *  ������⼸�����Ծ�������
 */
public class ListContainer {

	@Test
	public void test(){
		Collections.synchronizedList(new ArrayList<String>());
	}
	public void test1(){
		Collections.synchronizedMap(new HashMap<String,Object>());
	}
}
