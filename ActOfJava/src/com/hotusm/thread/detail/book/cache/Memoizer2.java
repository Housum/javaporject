package com.hotusm.thread.detail.book.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/**
 * 
 * @author Hotusm
 *
 * @param <A>
 * @param <V>
 * ��Ϊ��������ConcurrentHashMap�Ļ�  ���ǲ������������  ��Ϊ
 * ����ֵ�һ������ܾõĲ����Ļ�  ��ô�ͻ���ֶ������ͬʱ����
 * ����ʹ��Future<V> ����ʵ�� ������δ����  ���ڵ� �����ļ�����
 */
public class Memoizer2<A,V> implements Computable<A, V>{
	
	private final Computable<A,V> c;
	private final Map<A,Future<V>> cache=new ConcurrentHashMap<A,Future<V>>();
	
	public Memoizer2(Computable<A,V> c) {
		this.c=c;
	}
	
	/**
	 * ���ֳ��˺ܺõĲ�����  ������Ѿ���������� ��ô��ֱ�ӷ���  ������߳�����ʱ��  
	 * 
	 */
	@Override
	public V compute(final A arg) {
		//��Future���ڻ�����  �����Ļ�  ÿ�ζ������л�ȡ
		Future<V> result = cache.get(arg);
		//���ﲻ��ԭ���ԵĲ���  ���Ի������ظ����������  
		if(result==null){
			Callable<V> eval=new Callable<V>() {
				@Override
				public V call() throws Exception {
					//������
					return c.compute(arg);
				}
			};
			
			FutureTask<V> ft=new FutureTask<V>(eval);
			result=ft;
			//���ַ�ʽ�ͱ�����������˵������  ����һ��ԭ�ӵĲ���   
			cache.putIfAbsent(arg, ft);
			//cache.put(arg, ft);
			ft.run();
		}
		
		try {
			return result.get();
		} catch (Exception e) {
			e.printStackTrace();
			//������ִ���Ļ�  ��ô�ͻ����е���������  ��֤����Ի��������Ⱦ
			cache.remove(arg);
		}
		return null;
	}

}
