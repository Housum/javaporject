package com.hotusm.thread.detail.book;


public class UnsafeSequence {
	private int value;
	
	/**
	 * 看似这个是一个正常的操作 但是其实
	 * 这是一个线程不安全的操作  
	 * 
	 * 这种情况 我们称为竞态条件
	 * 
	 */
//	public int getValue(){
//		return value++;
//	}
	
	/**
	 * 上面的基础上  我们加上synchronized实现对象的方法同步锁机制(多个线程使用同一个对象的时候 调用方法时候值能一个线程调用)
	 */
	public synchronized int getValue(){
		return value++;
	}
	
	
}

