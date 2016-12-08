package com.hotusm.thread.detail.book;

/**
 * 每个JAVA对象都可以用作一个同步的锁
 * 这些锁称为内置锁(intrinsic)或监视锁(Monitor Lock)
 * 这是一种互斥锁 
 */
public class InnerLock {
	
	public synchronized void method1(){
		
	}
	public synchronized void method2(){
		method1();
		System.out.println("");
	}
	
	public static void main(String[] args) {
		/**
		 * 这是演示的是重入的一个概念
		 * 以为这种方式是JAVA对象维护的是一个锁
		 * 如果按照一般的情况 会出现死锁的情况 但是
		 * 如果是同一个线程的话  如果持有锁的话 那么
		 * 就可以多次的进入 每次进入的时候 都锁记录上加
		 * 1 退出一个锁区域的时候 减一 这样就能保持这个
		 * 锁的一个机制了 (可以通过DEBUG来查看这种方式)
		 * 
		 */
		new InnerLock().method2();
	}
}
