package com.hotusm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 
 * @author Hotusm
 * @since 2016-03-08
 * 定制ExecutorService,可以生产处自定义的线程池
 * 
 */
public class ThreadFactoryTest {
	
	public static void main(String[] args) {
		ExecutorService es=Executors.newCachedThreadPool(new DeamonFactory());
		for(int i=0;i<5;i++){
			es.execute(new DeamonThread());
		}
		//防止在继续生产线程
		es.shutdown();
		System.out.println("主线程:"+Thread.currentThread().toString());
	}
}
/**
 * 
 * @author Hotusm
 * 定制成生成的线程都是后台线程
 */
class DeamonFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		//这样只要是设置了DeamonFactory的Executors
		//那么生成出来的线程都是后台线程
		Thread t=new Thread(r);
		//t.setDaemon(true);
		return t;
	}
	
}
//定制自己的Factory
class DeamonThread implements Runnable{

	@Override
	public void run() {
		while(true){
			System.out.println(Thread.currentThread().toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}






