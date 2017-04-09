package com.hotusm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java并发的基础
 * 1.ExecutorService可以执行线程,而不用通过Thread.start()这种方式进行.
 * 通过这种方式可以更好的管理线程
 * 2.通过Executors的静态方法获取到线程管理器
 * ①Executors.newCachedThreadPool();为每一个线程
 * 都创建一个线程
 * ②Executors.newFixedThreadPool(nThreads);创建执行数量的线程数
 * ③Executors.newSingleThreadExecutor();创建单独的线程，如果有很多
 * 和任务,那么这些任务会进行排序，执行完一个任务在执行下一个任务
 * ④Executors.newScheduledThreadPool(corePoolSize)创建一个
 * 任务列表和②相似
 * 
 * @author Hotusm
 * @since 2016-03-07
 */
public class ExecutorTest {

	public static void main(String[] args) {
		ExecutorService es=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			es.execute(new RunnableImpl());
		}
		//停止可以防止新的任务被提交到ExecutorService
		es.shutdown();
	}
}
// the implement of Runnable
class RunnableImpl implements Runnable{

	int i;
	@Override
	public void run() {
		while(i<20){
			
			System.out.println(i++);
			/**
			 * 表示的是告诉线程管理器:当前线程已经执行好了最主要的部分
			 * 可以执行其他的线程了
			 * 
			 */
			Thread.yield();

		}
	}
	
}
