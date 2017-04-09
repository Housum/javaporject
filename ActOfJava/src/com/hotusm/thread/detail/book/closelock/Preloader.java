package com.hotusm.thread.detail.book.closelock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * FutureTask 也可以实现闭锁的功能 
 * 参数是一个有返回值的线程Callable  
 * FutureTask.get()会返回结果   
 * 如果结果还没有好 那么线程会一直的堵塞
 */
public class Preloader {
	
	private final FutureTask<ProductInfo> future=
		new FutureTask<ProductInfo>(new Callable<ProductInfo>() {

			@Override
			public ProductInfo call() throws Exception {
				try {
					return new ProductInfo("预先加载的产品信息");
				} catch (Exception e) {
					//如果出现错误 那么就抛出这个  数据加载的异常
					throw new DataLoadException();
				}
				
			}
		});
	private final Thread thread=new Thread(future);
	
	public void start(){
		thread.start();
	}
	/**
	 * 获取值  如果获取不了的话  那么就会一直的堵塞  
	 * 这里的异常  处理的非常好 需要重视  
	 * 
	 * @return
	 * @throws InterruptedException
	 * @throws DataLoadException
	 */
	public ProductInfo get() throws InterruptedException, DataLoadException{
		try {
			return future.get();
		} catch (ExecutionException e) {
			Throwable cause=e.getCause();
			if(cause instanceof DataLoadException){
				throw (DataLoadException)cause;
			}else{
				throw launderThrowable(cause);
			}
		}	
	}
	
	public static RuntimeException launderThrowable(Throwable cause){
		if(cause instanceof RuntimeException)
			return (RuntimeException)cause;
		else if(cause instanceof Error)
			throw (Error)cause;
		else
			throw new IllegalStateException("Illeagal state");
	}
	
	
	
	
}
