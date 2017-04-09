package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.thread.detail.start.Log;

/**
 * 
 * 
 *@link http://blog.csdn.net/yinwenjie/article/details/50522458
 *
 */
public class PoolThread {
	
	public static void main(String[] args) {
	     /*
         * corePoolSize：核心大小，线程池初始化的时候，就会有这么大
         * maximumPoolSize：线程池最大线程数
         * keepAliveTime：如果当前线程池中线程数大于corePoolSize。
         * 多余的线程，在等待keepAliveTime时间后如果还没有新的线程任务指派给它，它就会被回收
         * 
         * unit：等待时间keepAliveTime的单位
         * 
         * workQueue：等待队列。这个对象的设置是本文将重点介绍的内容
         * */
		ThreadPoolExecutor executor=new ThreadPoolExecutor(5, 10,1,TimeUnit.MINUTES, new SynchronousQueue<Runnable>());
		
		//这里是创建了10个线程  因为我们设置了corePoolSize为5  当任务执行完以后  过了keepAliveTime时间以后  
		//那么这些非核心线程会被移除线程池中
		for(int i=0;i<10;i++){
			executor.submit(new TestThread(i));
		}
		
		//设置核心线程在等待keepAliveTime之后  进行回收 设置了这句话以后  DEBUG的时候 我们
		//就可以发现在等待一段时间以后   线程全部死亡了
		//executor.allowCoreThreadTimeOut(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//通过实践发现:当我们没有放任何的任务上去的话  那么是不会创建任何的任务的
		//prestartAllCoreThreads设置项，可以在线程池创建，但还没有接收到任何任务的情况下，先行创建符合corePoolSize参数值的线程数：
		//executor.prestartAllCoreThreads();
		
	}
}
class TestThread implements Runnable{
	
	private int index;
	
	public TestThread(int index){
		this.index=index;
	}

	@Override
	public void run() {
		Thread thread=Thread.currentThread();
		Log.info("线程:"+thread.getId()+"中的任务("+this.index+"正在运行");
		//这个表示的是当前的线程
		synchronized(thread){
			try {
				thread.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Log.info("线程:"+thread.getId()+"中的任务("+this.index+"执行完成");
	}
}
