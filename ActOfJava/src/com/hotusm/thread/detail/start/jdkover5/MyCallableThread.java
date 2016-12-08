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
 *  ĿǰCallable������߳�����ֻ�ܷ����̳߳��У����̳߳��е��������ִ�С�û��������Runnable�ӿ�������new Thread(new MyDefindRunnable())����start()���߳����з�ʽ��
 *
 *	���������ʹ���̳߳ع��������ִ�У��ֲ���ֱ�ӽ�Callable�ӿڵ��������Thread����ô��ֻ�ܽ���һ�������ࣺFutureTask��ʹ�÷�ʽ���£�
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
		//ģ��ҵ��  �߳���ͣ5��  ����Դ�ó�ȥ
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
        //�����������һ��ģ�Ͷ���������һ��status����
        MyCallableThread<Entity> callableThread = new MyCallableThread<Entity>(new Entity());

        /**
         * ���ǵ�һ����ʽ:
         */
        // Callable��Ҫ���̳߳���ִ��
//        ExecutorService es = Executors.newFixedThreadPool(1);
//        Future<Entity> future = es.submit(callableThread);
//
//        // main�̻߳�������ȴ���֪��callableThread����ִ�����
//        Entity result = future.get();
//        System.out.println("result.status = " + result.getStatus());
//        // ֹͣ�̳߳ع���
//        es.shutdown();
//        es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        /**
         * ���ǵڶ�����ʽ:
         * ��ΪCallableֻ�����̳߳������в���  
         * ���ǲ����̳߳صĻ�  ����ʹ������ķ�ʽ
         */
        FutureTask<Entity> task=new FutureTask<Entity>(callableThread);
        new Thread(task).start();
        Entity entity = task.get();
        Log.info("the result��"+entity.getStatus());
        
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