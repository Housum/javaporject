package com.hotusm.thread;

/**
 * 
 * @author Hotusm
 * @since 2016-03-13
 * join的用法 
 * 
 * 如果一个线程a在b线程中使用a.join(),那么b线程会一直
 * 等待a线程执行完毕之后,才会继续执行b线程的任务
 * 
 * 
 */
public class JoinTest {
	
	public static void main(String[] args) {
		Sleeper s=new Sleeper();
		Join j=new Join(s);
	}
}

class Sleeper extends Thread{
	
	public Sleeper() {
		start();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("结束休眠...");
	}
}
class Join extends Thread{
	
	private Sleeper sleeper;

	public Join(Sleeper sleeper) {
		super();
		this.sleeper = sleeper;
		start();
	}

	@Override
	public void run() {
		/**
		 * 线程Sleeper在其他的线程Join中调用了
		 * join方法,那么Join线程就会等待Sleeper线程执行完成
		 * 以后，才会执行.
		 *  
		 */
		try {
			this.sleeper.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Join结束");
	}
	
}
