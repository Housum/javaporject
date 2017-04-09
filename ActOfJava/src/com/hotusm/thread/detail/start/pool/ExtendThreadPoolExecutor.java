package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.thread.detail.start.Log;
/**
 * 2.一个扩展的线程池，用于测试ThreadPoolExecutor中的扩展方法
 *	beforeExecute：当线程池正要开始执行某个任务的时候（注意不是任务进入等待队列的时候，是将要开始正式在线程池中执行的时候），线程池会触发这个方法的调用。
 *
 *	afterExecute：当线程池完成了某一个任务的执行后，线程池就会触发这个方法。
 *
 *	terminated：当线程池本身停止执行的时候，该方法就会被调用。
 *
 *2.executor.submit和executor.execute的不同点
 *	executor.submit:
 *使用submit方法提交的实现了Runnable接口的任务，将会被封装到 线程池内部使用Executors工具中callable方法创建的RunnableAdapter对象中。
 *最终是调用这个方法: @see java.util.concurrent.Executors.callable(Runnable, T)
 *  executor.execute:
 *这种方式就是说将任务进行分配  内部会进行判断  是否能够添加或者不能添加   抛出异常
 *
 *3.需要注意的一点就是执行下面的这三个方法的线程是线程池中的运行线程  所以我们可以看到
 *<code>
 *  private final ThreadLocal<Long> startTime=new ThreadLocal<Long>();
 *	private final ThreadLocal<String> threadName=new ThreadLocal<String>();
 *</code>
 *这两个方法都是同一个线程共享变量的
 *
 */
public class ExtendThreadPoolExecutor extends ThreadPoolExecutor{
	
	/**
	 * ThreadLocal同于共享一个线程中的变量
	 */
	private final ThreadLocal<Long> startTime=new ThreadLocal<Long>();
	private final ThreadLocal<String> threadName=new ThreadLocal<String>();
	
	public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		startTime.set(System.nanoTime());
		threadName.set(t.getName());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Long totalTime=System.nanoTime()-startTime.get();
		Log.info(threadName.get()+"thread  totalTIme:"+totalTime);
	}

	@Override
	protected void terminated() {
		Log.info("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExtendThreadPoolExecutor executor=new ExtendThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
		for(int i=0;i<10;i++){
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
//			executor.submit(new Runnable() {
//				
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(2000);
//						Log.info("线程:"+Thread.currentThread().toString()+"正在运行");
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			});
		}
		/**
		 *          发出停止指令。注意停止指令本身不会等待，要使用awaitTermination进行等待。
         *			注意，按照我们上文讲过的线程池的工作原理，线程池在收到shutdown终止指令后
         * 			就不会再接受提交过来的任务了，无论“核心线程”、等待队列处于什么样的状态！
		 */
		executor.shutdown();
		
		 // 当所有任务执行完成后，终止线程池的运行
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
	}
	
	
}
