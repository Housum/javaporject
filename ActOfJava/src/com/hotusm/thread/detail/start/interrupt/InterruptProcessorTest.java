package com.hotusm.thread.detail.start.interrupt;

import com.hotusm.thread.detail.start.Log;

/**
 * 如果在收到interrupt信号时，线程处于阻塞状态（wait()、wait(time)或者sleep引起的,
 * 那么线程将会抛出InterruptedException异常:
 * 如果是运行的时候  那么就会中断 
 * 
 * @link http://blog.csdn.net/yinwenjie/article/details/50510915#java
 * 
 * thread.isInterrupted()和Thread.interrupted()的区别:
 * 这就意味着当某个线程的isInterrupt属性成功被置为true后，如果您使用对象方法thread.isInterrupted()获取值，
 * 无论您获取多少次得到的返回值都是true；但是如果您使用静态方法Thread.interrupted()获取值,
 * 那么只有第一次获取的结果是true，随后线程的isInterrupt属性将被恢复成false,
 * 后续无论使用Thread.interrupted()调用还是使用thread.isInterrupted()调用,
 * 获取的结果都是false.
 * 
 *
 */
public class InterruptProcessorTest {
	
	public static void main(String[] args) {
		
		Thread a=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Thread thread=Thread.currentThread();
				while(!thread.isInterrupted()){
					Log.info("线程a一直在执行呢");
				}
			}
		});
		
		Thread b=new Thread(new Runnable() {
			
			@Override
			public void run() {
				Thread currentThread = Thread.currentThread();
				while(!currentThread.isInterrupted()){
					
					//如果一个线程处于  wait或者是sleep的时候   触发interrupt方法的时候  那么会抛出InterruptedException
					synchronized (currentThread) {
						try {
							currentThread.wait();
						} catch (InterruptedException e) {
							//在后面调用b.interrupt()的时候  会抛出异常
							System.out.println("b线程由于中断型号,异常结束   "+currentThread.isInterrupted());
							e.printStackTrace(System.out);
							return;
						}
					}
					
				}
			}
		});
		
		a.start();
		//b.start();
		Log.info("两个线程正常运行,现在开始发出中断信号");
		
		a.interrupt();
		//b.interrupt();
		
		try {
			InterruptProcessorTest.class.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
