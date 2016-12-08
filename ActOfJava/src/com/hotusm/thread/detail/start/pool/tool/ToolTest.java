package com.hotusm.thread.detail.start.pool.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * Executors 是一个创建线程的工具类
 * 这些工具类创建的线程池使用的BlockingQueue都是LinkedBlockingQueue
 * 所以线程可以无限的添加(但是能够同时执行的线程是不同的概念)
 */
public class ToolTest {

	@Test
	public void testNewFixedThreadPoolMethod(){
		/**
		 * 这个方法创建指定容量的线程池 
		 * 创建是java.util.concurrent.ThreadPoolExecutor
		 * corePoolSize和maximumPoolSize是相同的 keepAliveTime设置
		 * 意思就是我们指定的参数是多大 那么线程池就是多大  线程池中的都是核心线程
		 * 
		 */
		ExecutorService executors = Executors.newFixedThreadPool(10);
		for(int i=0;i<15;i++){
			executors.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.info("线程正在运行");
				}
			});
		}
		synchronized(this){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 这个方法创建指定容量的线程池 
	 * 创建是java.util.concurrent.ThreadPoolExecutor
	 * corePoolSize和maximumPoolSize都设置为1  说明线程池中只能够执行一个任务
	 * 
	 */
	@Test
	public void testNewSingleThreadExecutor(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		/**
		 * 下面的这两个线程任务  虽然在任务中调用了wait()方法将资源给让了出去   
		 * 但是另外一个任务却不能够执行  只能等一个任务结束了以后  才能够开始另外一个任务 
		 * 这就很好的说明了上面的理论  线程池中只会创建一个任务 
		 * 如果有多个的话  那么就会一直等待前面的任务完成了
		 */
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				synchronized (this) {
					try {
						this.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Log.info("线程1结束");
			}
		});
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				synchronized (this) {
					try {
						this.wait(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Log.info("线程2结束");
			}
		});
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 这种类型的corePoolSize为0,maximumPoolSize为Integer.MAX_VALUE
	 * 空闲时间设置为60S .所以这个是一个缓存线程池
	 * 
	 */
	@Test
	public void testNewCachedThreadPool(){
		final AtomicInteger index=new AtomicInteger(1);
		
		ExecutorService executors = Executors.newCachedThreadPool();
		for(int i=0;i<50;i++){
			executors.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.info("这是"+index.getAndIncrement()+"线程");
				}
			});
		}
		
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
