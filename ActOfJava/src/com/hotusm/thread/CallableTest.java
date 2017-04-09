package com.hotusm.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 具有返回值的线程
 * @author Hotusm
 * @since 2016-03-07
 */
public class CallableTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService es=Executors.newCachedThreadPool();
		List<Future<String>> results=new ArrayList<Future<String>>();
		Future<String> submit = es.submit(new TaskWithResult());
		String string = submit.get();
		String string2 = submit.get();
		String string3 = submit.get();
		System.out.println(string+" "+string2+" "+string3);
	}
}
/**
 * Callable需要和Future还有ExecutorService一同使用
 * 
 * @author Hotusm
 *
 */
class TaskWithResult implements Callable<String>{
	int i;
	@Override
	public String call() throws Exception {
		return Math.random()+"";
	}
}