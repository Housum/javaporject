package com.hotusm.thread.detail.book.closelock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * FutureTask Ҳ����ʵ�ֱ����Ĺ��� 
 * ������һ���з���ֵ���߳�Callable  
 * FutureTask.get()�᷵�ؽ��   
 * ��������û�к� ��ô�̻߳�һֱ�Ķ���
 */
public class Preloader {
	
	private final FutureTask<ProductInfo> future=
		new FutureTask<ProductInfo>(new Callable<ProductInfo>() {

			@Override
			public ProductInfo call() throws Exception {
				try {
					return new ProductInfo("Ԥ�ȼ��صĲ�Ʒ��Ϣ");
				} catch (Exception e) {
					//������ִ��� ��ô���׳����  ���ݼ��ص��쳣
					throw new DataLoadException();
				}
				
			}
		});
	private final Thread thread=new Thread(future);
	
	public void start(){
		thread.start();
	}
	/**
	 * ��ȡֵ  �����ȡ���˵Ļ�  ��ô�ͻ�һֱ�Ķ���  
	 * ������쳣  ����ķǳ��� ��Ҫ����  
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
