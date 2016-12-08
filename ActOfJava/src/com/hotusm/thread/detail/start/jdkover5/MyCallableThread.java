package com.hotusm.thread.detail.start.jdkover5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import com.hotusm.thread.detail.start.Log;
/**
 * 
 *  目前Callable定义的线程任务，只能放入线程池中，由线程池中的任务进行执行。没有类似于Runnable接口那样，new Thread(new MyDefindRunnable())并且start()的线程运行方式。
 *
 *	如果您不想使用线程池管理任务的执行，又不能直接将Callable接口的任务放入Thread，那么您只能借助一个工具类：FutureTask。使用方式如下：
 *	FutureTask<Entity> futureTask = new FutureTask<Entity>(callableThread);
 *	new Thread(futureTask).start();
 *
 * @param <V>
 */
public class MyCallableThread<V extends Entity> implements Callable<V>{

	private V resultEntity;
	
	public MyCallableThread(V resultEntity){
		this.resultEntity=resultEntity;
	}
	
	@Override
	public V call() throws Exception {
		//模拟业务  线程先停5秒  将资源让出去
		try {
			synchronized(this){
				this.wait(5000);
				//Thread.sleep(5000);
			}
			this.resultEntity.setStatus("1");
		} catch (Exception e) {
			this.resultEntity.setStatus("-1");
		}
		
		return resultEntity;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
        //这是您定义的一个模型对象。里面有一个status属性
        MyCallableThread<Entity> callableThread = new MyCallableThread<Entity>(new Entity());

        /**
         * 这是第一种形式:
         */
        // Callable需要在线程池中执行
//        ExecutorService es = Executors.newFixedThreadPool(1);
//        Future<Entity> future = es.submit(callableThread);
//
//        // main线程会在这里等待，知道callableThread任务执行完成
//        Entity result = future.get();
//        System.out.println("result.status = " + result.getStatus());
//        // 停止线程池工作
//        es.shutdown();
//        es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        /**
         * 这是第二种形式:
         * 因为Callable只能由线程池来进行操作  
         * 但是不用线程池的话  可以使用下面的方式
         */
        FutureTask<Entity> task=new FutureTask<Entity>(callableThread);
        new Thread(task).start();
        Entity entity = task.get();
        Log.info("the result："+entity.getStatus());
        
	}
}

class Entity{
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}