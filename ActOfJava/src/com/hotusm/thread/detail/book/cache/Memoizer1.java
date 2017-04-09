package com.hotusm.thread.detail.book.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer1<A,V> implements Computable<A, V>{

	/**
	 * ʹ��HashMap���ַ�ʽ����������  ��Ϊ�����̰߳�ȫ��  ��������
	 * ��compute������  ����ʹ��synchronize  ͬ��
	 */
	//private final Map<A,V> cache=new HashMap<A,V>();
	/**
	 * ConcurrentHashMap���̰߳�ȫ�� �������ܱ�Hashtable��  
	 */
	private final Map<A,V> cache=new ConcurrentHashMap<A,V>();
	
	private final Computable<A, V> c;
	
	public Memoizer1(Computable<A, V> c) {
		this.c=c;
	}
	
	/**
	 * ��Ϊʹ�õ���HashMapʵ�ֵ�  ����HashMap�������̰߳�ȫ��
	 * ����  ������Ҫʹ���������ķ�ʽ  
	 * @param arg
	 * @return
	 */
	/*@Override
	public synchronized V compute(A arg) {
		V result=cache.get(arg);
		if(result==null){
			result=c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}*/
	
	/**
	 * ʹ��ConcurrentHashMap���̰߳�ȫ�� �������ǲ���ʹ�ò�ͬ����
	 * �������ַ�ʽ���ǻ�������  ��Ϊ������һ���ķ�ʱ��Ĳ�����ʱ��    ��ô�ͻ�
	 * ����ͬһ��A ��ʱ��  ���ɼ�����
	 * @see com.hotusm.thread.detail.book.cache.Memoizer2<A, V>
	 */
	public V compute(A arg){
		V result=cache.get(arg);
		if(result==null){
			result=c.compute(arg);
			cache.put(arg, result);
		}
		
		return result;
	}
	
}
