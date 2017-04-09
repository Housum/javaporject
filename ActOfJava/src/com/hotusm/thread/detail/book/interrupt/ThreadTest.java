package com.hotusm.thread.detail.book.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * @see Thread
 * public void interrupt();     			将线程中断
 * public boolean interrupted();   			判断线程是否中断
 * public static boolean interrupted();     清理<strong>当前</strong>的线程的中断状态
 * 											并且返回中断状态
 * 
 */
public class ThreadTest {
	
	/**
	 * 堵塞库方法 例如Thread.sleep() Object.await()等 都会检查
	 * 线程何时中断，并且在发现中断时提前返回.他们在相应中断的执行的操作包括
	 * 清除中断状态 ,抛出InterruptException 表示堵塞操作由于中断而提前结束
	 *
	 *在下面的这个例子中可以看出来  
	 *1.使用<code>Thread.sleep(int timeout)</code>让线程堵塞  在堵塞的时候调用interrupt()方法  会使
	 *堵塞的方法提前返回 并且清理中断的状态  从ouput中我们可以看出
	 *2.这个时候我们需要对堵塞过程中中断做出处理 
	 *最简单的处理就是将线程中断(因为这个时候中断状态已经被清除了)
	 *我们可以直接调用<code>Thread.currentThread().interrupt();</code>
	 */

	@Test
	public void testBlock(){
		Thread th = new Thread(){
			@Override
			public void run() {
				try {
					System.out.println("Job start");
					Thread.sleep(10000);
					System.out.println("Job stop");
				} catch (InterruptedException e) {
					//e.printStackTrace();
					System.out.println("current thread interrupt : "+Thread.currentThread().isInterrupted());
					
				}
			}
		};
		
		th.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(!th.isInterrupted()){
			th.interrupt();
		}
		
		
	}
	
	/**
	 * 在一般的情况下  调用线程停止的操作
	 */
	@Test
	public void testSimpleInterrupt(){
		
		
		Thread th = new Thread(){
			
		  public int index=0;
			
			@Override
			public void run() {
				while(true){
					synchronized (this) {
						
						if(Thread.currentThread().isInterrupted()){
							boolean isInterrupt=Thread.interrupted();
							System.out.println(isInterrupt);
							if(isInterrupt){
								try {
									throw new InterruptedException();
								} catch (InterruptedException e) {
									e.printStackTrace();
									System.exit(1);
								}
							}
						}
						/**
						 * 只要我们调用了interrupt()方法 线程的中断状态被马上的设置为了true  但是
						 * 线程并不一定会立即的停止
						 * 
						 */
						//if(!Thread.currentThread().isInterrupted()){
							if(index==0){
								/**
								 * 这里会输出两次  第一次是false
								 * 第二次是true
								 */
								Log.info("Thread interrupt is : "+Thread.currentThread().isInterrupted());
							}
							Log.info("index:"+index++);

						//}
					}
				}
			}
			
			
			/**
			 * 1.对于在真实的环境中 我们在中断前都需要做一些处理  
			 * 比如清理资源 关闭资源之类的
			 * 2.在这个例子中我们使用了内置锁 保证了run()方法和interrupt()
			 * 只能同时的执行一个方法 所以理想的情况下 输出的来的index是一直增长的 不会出现
			 * 又从0开始的情况  但是实际测试中发现确实出现了重新从0开始输出的情况 
			 * 出现这个问题的原因是因为JVM并不能保证方法检测到中断的速度.(
			 * 实际上是非常的快的 但是还是有很少的延迟  这就是为什么出现又从0开始输入的原因)
			 * 真实输出:
			 * 	index:203750
				index:203751
				do something before stop
				index:0
				index:1
				index:2
				上面简单的来说就是虽然调用了interrupt()方法 但是并不能确定线程马上的停下来
				所以可能还会执行很小的一段时间 但是<strong>我们可以立即的知道线程的状态
				具体的方法就是调用线程的isInterruptd()方法   
				具体可以看run方法注释的那部分 (对于要求响应非常高的大部分都是这样使用的 )去掉注释的话 那么输出的是:
				index:164531
				index:164532
				index:164533
				index:164534
				do something before stop
				
				</strong>
				
			 */
			@Override
			public void interrupt() {
				synchronized (this) {
					index=0;
					System.out.println("do something before stop");
					super.interrupt();
				}
			
			}
			
		};
		
		th.start();
		
		/*
		 * 虽然th线程是while(true){}的形式运行
		 * 但是因为我们在主线程拥有th线程  我们可以直接
		 * 调用
		 * 
		 * */
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(!th.isInterrupted()){
			th.interrupt();
		}
		
	}
	
	
	/**
	 * 测试生成素数  
	 * 当我们调用interrupt方法的时候  能够将运行中或者堵塞状态的线程中断
	 */
	@Test
	public void testPrimeProducer(){
		PrimeProducer primeProducer = new PrimeProducer(new ArrayBlockingQueue<>(20));
		primeProducer.start();
		
		primeProducer.cancel();
		Log.info("stop....");
	}
	
	/**
	 * 在IO堵塞的时候 或者等待内置锁而堵塞 那么中断请求只能设置线程的中断状态
	 * 除此之外没有任何的作用  对于那些由于执行不可中断操作而被堵塞的线程,可以
	 * 使用类似于中断的手段来停止这些线程
	 * 
	 * 1.Java.io中同步的Socket I/O InputStream 和OutputStream 中的read和write方法
	 * 都不会相应中断 但是我们可以关闭底层的套接字 ，可以使得这些方法被堵塞
	 * 2.Java.io中的同步IO
	 * 3.获取某一个锁 在获取内置锁的时候  不会相应中断 
	 * 但是<strong>Lock.lockInterruptibly方法 ,该方法允许在等待一个锁的时仍能相应中断</strong>
	 * 4....
	 * @see  <title>JAVA 编程实战</title>
	 * 
	 */
	public static void main(String[] args) {
		
		Thread th = new Thread(){
			
			private ServerSocket ss;
			//因为Socket 的堵塞不能对中断进行相应
			//所以在运行中我们通过interrupt()不能中断这个线程
			//但是我们可以使用flag控制循环是否运行
			private volatile boolean flag=true;
			public void run() {
				try {
					ss = new ServerSocket(8090);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				while(flag){
					try {
						if(!Thread.currentThread().isInterrupted()){
							Socket s = ss.accept();
							handleRequest(s);
						}else{
							
						}
					} catch (IOException e) {
						//ignore
					}
				}
			};
			
			private void handleRequest(Socket s){
				try {
					InputStream in = s.getInputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void interrupt() {
				try {
					ss.close();
					flag=false;
				} catch (IOException e) {
					//e.printStackTrace();
					//ignore
				}
				super.interrupt();
			}
		};

		th.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th.interrupt();
		
	}
	
	/**
	 * 对于普通的无限循环  我们可以通过中断线程的方式 将其进行中断
	 * 
	 */
	@Test
	public void testCycle(){
		Thread th = new Thread(){
			@Override
			public void run() {
				while(true){
					Log.info("edit");
				}
			}
		};
		th.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/**
		 * 调用之后能够中断线程
		 */
		th.interrupt();
	}
	
	/**
	 * 执行一种取消策略
	 * @param r
	 * @param timeout
	 * @param unit
	 */
	public void timedRun(final Runnable r,long timeout,TimeUnit unit){
		
		ScheduledExecutorService executor=Executors.newScheduledThreadPool(10);
		
		class RethrowableTask implements Runnable{
			
			private volatile Throwable throwable;
			@Override
			public void run() {
				try {
					r.run();
				} catch (Exception e) {
					this.throwable=e;
				}
			}
			
			public Throwable rethorw() throws Throwable{
				throw throwable;
			}
		};
		RethrowableTask task=new RethrowableTask();
		final Thread taskThread=new Thread(task);
		taskThread.start();
		executor.schedule(new Runnable() {
			
			@Override
			public void run() {
				taskThread.interrupt();
			}
		}, timeout, unit);
		
		try {
			taskThread.join(unit.toMillis(timeout));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			task.rethorw();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
class PrimeProducer extends Thread{
	
	private final BlockingQueue<Object> nodes;
	private final Random random=new Random();
	
	public PrimeProducer(BlockingQueue<Object> nodes) {
		this.nodes=nodes;
	}
	
	@Override
	public void run() {
		try {
			//在调用前线判断线程是否是中断状态
			while(!Thread.currentThread().isInterrupted()){
				//当BlockingQueue因为生产者满了以后  线程堵塞住  我们
				//可以调用cancel()将线程进行中断  
				nodes.put(random.nextInt(100));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Log.info("PrimeProducer stop");
	}
	
	public void cancel(){
		interrupt();
	}
}
