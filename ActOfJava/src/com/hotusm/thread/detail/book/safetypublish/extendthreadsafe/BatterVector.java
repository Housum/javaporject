package com.hotusm.thread.detail.book.safetypublish.extendthreadsafe;

import java.util.Vector;
/**
 * 在现有的线程安全类中添加功能
 * 下面实现的是一个如果不存在  那么就添加的功能  如果存在  那么就添加
 */
public class BatterVector<E> extends Vector<E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 和父类保持了一样的同步策略  所以是没有问题的.但是有可能会遇到我们
	 * 不知道父类的同步策略的情况 
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
