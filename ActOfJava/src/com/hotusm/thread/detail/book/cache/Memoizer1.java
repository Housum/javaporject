package com.hotusm.thread.detail.book.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer1<A,V> implements Computable<A, V>{

	/**
	 * 使用HashMap这种方式性能是最差的  因为不是线程安全的  所以我们
	 * 在compute方法中  必须使用synchronize  同步
	 */
	//private final Map<A,V> cache=new HashMap<A,V>();
	/**
	 * ConcurrentHashMap是线程安全的 并且性能比Hashtable高  
	 */
	private final Map<A,V> cache=new ConcurrentHashMap<A,V>();
	
	private final Computable<A, V> c;
	
	public Memoizer1(Computable<A, V> c) {
		this.c=c;
	}
	
	/**
	 * 因为使用的是HashMap实现的  但是HashMap并不是线程安全的
	 * 所以  我们需要使用内置锁的方式  
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
	 * 使用ConcurrentHashMap是线程安全的 所以我们不用使用不同方法
	 * 但是这种方式还是会有问题  因为当计算一个耗费时间的操作的时候    那么就会
	 * 出现同一个A 的时候  依旧计算多遍
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
