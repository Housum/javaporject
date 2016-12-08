package com.hotusm.thread.detail.book;
/**
 * 1.线程的可见性也是一个比较严重的问题
 *  下面的这个程序就会产生可见性的问题  虽然在main方法中设置了ready=true
 *  在 ReaderThread 中线程能够继续执行下去  但是很意外的是 并不会这样做  就好像是
 *  ready一直等于false 因为在主线程改变的值 对于ReaderThread线程来说是不可见的
 * 2.虽然数据的可见性有问题  当线程在没有同步的情况下 可能会得到一个失效值,但是至少是之前的
 * 某个线程的值 而不是一个随机数 这叫做最低安全性
 *  
 */
public class NoVisibility {
	
	private static boolean ready;
	private static int number=0;
	
	private static class ReaderThread extends Thread{
		
		/**
		 * yield() 释放当前的线程 (但是有资源的时候 那么就会继续执行这个线程)
		 * 这个方法的作用就是为了防止一个线程占用太久的时间
		 */
		@Override
		public void run() {
				/**
				 * 
				 */
				while(!ready)Thread.yield();
				System.out.println(number);
		}
		
	} 
	public static void main(String[] args) {
//		ReaderThread t1 = new ReaderThread();
//		t1.start();
		new ReaderThread().start();
		synchronized (NoVisibility.class) {
			try {
				NoVisibility.class.wait(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number=42;
		ready=true;
		//一直等待t1执行完线程
//		try {
//			t1.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}