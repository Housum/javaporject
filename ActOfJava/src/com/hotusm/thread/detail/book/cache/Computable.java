package com.hotusm.thread.detail.book.cache;
/**
 * 
 * @author Hotusm
 *
 * @param <A>
 * @param <V>
 * ����A  ���V  
 * 
 */
public interface Computable<A,V>{
	
	public V compute(A arg);
}
