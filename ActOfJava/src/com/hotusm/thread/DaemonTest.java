package com.hotusm.thread;

/**
 * 1.后台线程:
 * 后台线程是指在程序运行的时候在后台提供一种通用服务的线程，并且这种线程并不是程序中不可或缺的部分
 * 因此当前台线程都结束的时候,此时后台线程也会结束
 * 2.线程的优先级:
 * 在大多数的情况下,线程都应该以默认的优先级执行。试图操作线程的优先级是一种错误
 * 
 * @author Hotusm
 * @since 2016-03-07
 */
public class DaemonTest extends Thread{

	int i;
	@Override
	public void run() {
		
		//虽然在这里设置了条件,线程会一直的执行,但是在实际的主线程中,我们设置了子线程为
		//后台线程,而主线程是前台线程,所以在前台线程执行完成以后，后台线程自然就停止了.
		while(true){
			System.out.println(Thread.currentThread().toString());
			//通过设置当前线程的优先级,数值越大，线程的优先级越高,注意:虽然设置了优先级
			//但是不能确定一定是按照这个优先级执行的，并且不能说优先级低的就被阻塞了,只能
			//理解成优先级低的执行的频率会比较低一些
			Thread.currentThread().setPriority(10);
			System.out.println(i++);
		}
	}
	
	public static void main(String[] args) {
		DaemonTest dt=new DaemonTest();
		//如果把setDaemon()注释掉,那么线程会一直的执行,没有注释的情况下，主线程执行完成
		//以后，后台线程也相应的结束
		dt.setDaemon(true);
		dt.start();
		//线程默认的优先级都是一样的,可以Thread.currentThread().setPriority()
		//来设置线程的优先级
		int priority2 = Thread.currentThread().getPriority();
		System.out.println("主线程的优先级:"+priority2);
		System.out.println(Thread.currentThread().toString());
	}
	
}
