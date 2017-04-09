package com.hotusm.thread.detail.start;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 *@java.util.concurrent.Future<V>
 *表示异步计算的结果  只有在计算结束的时候才能够返回结果  不然的话  就会堵塞
 *或者我们可以通过cancel方法进行取消
 */
public class FutureTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor=new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES,  new SynchronousQueue<Runnable>());
		Future<String> future = executor.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				Log.info("线程"+Thread.currentThread().getName()+"正在运行");
				//如果这里加上这一句话  那么	Log.info("result: "+future.get());
				//这个只能在等待两秒以后才能够返回值  因为任务还没完成的话  那么就会一直堵塞 直到计算结束
				Thread.sleep(2000);
				return "the result";
			}
			
		});
		
		Log.info("线程"+Thread.currentThread().getName()+"正在运行");
		//只能等线程结束返回信息
		Log.info("future: "+future.get());
		
		Future<String> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
				Log.info("线程"+Thread.currentThread().getName()+"正在运行");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "ok");
		
		//只能等线程完成以后  才会将第二个参数中的结果返回
		Log.info("future1: "+future1.get());
		
	}
}
