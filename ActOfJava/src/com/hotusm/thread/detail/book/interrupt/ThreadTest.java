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
 * public void interrupt();     			���߳��ж�
 * public boolean interrupted();   			�ж��߳��Ƿ��ж�
 * public static boolean interrupted();     ����<strong>��ǰ</strong>���̵߳��ж�״̬
 * 											���ҷ����ж�״̬
 * 
 */
public class ThreadTest {
	
	/**
	 * �����ⷽ�� ����Thread.sleep() Object.await()�� ������
	 * �̺߳�ʱ�жϣ������ڷ����ж�ʱ��ǰ����.��������Ӧ�жϵ�ִ�еĲ�������
	 * ����ж�״̬ ,�׳�InterruptException ��ʾ�������������ж϶���ǰ����
	 *
	 *���������������п��Կ�����  
	 *1.ʹ��<code>Thread.sleep(int timeout)</code>���̶߳���  �ڶ�����ʱ�����interrupt()����  ��ʹ
	 *�����ķ�����ǰ���� ���������жϵ�״̬  ��ouput�����ǿ��Կ���
	 *2.���ʱ��������Ҫ�Զ����������ж��������� 
	 *��򵥵Ĵ�����ǽ��߳��ж�(��Ϊ���ʱ���ж�״̬�Ѿ��������)
	 *���ǿ���ֱ�ӵ���<code>Thread.currentThread().interrupt();</code>
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
	 * ��һ��������  �����߳�ֹͣ�Ĳ���
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
						 * ֻҪ���ǵ�����interrupt()���� �̵߳��ж�״̬�����ϵ�����Ϊ��true  ����
						 * �̲߳���һ����������ֹͣ
						 * 
						 */
						//if(!Thread.currentThread().isInterrupted()){
							if(index==0){
								/**
								 * ������������  ��һ����false
								 * �ڶ�����true
								 */
								Log.info("Thread interrupt is : "+Thread.currentThread().isInterrupted());
							}
							Log.info("index:"+index++);

						//}
					}
				}
			}
			
			
			/**
			 * 1.��������ʵ�Ļ����� �������ж�ǰ����Ҫ��һЩ����  
			 * ����������Դ �ر���Դ֮���
			 * 2.���������������ʹ���������� ��֤��run()������interrupt()
			 * ֻ��ͬʱ��ִ��һ������ �������������� ���������index��һֱ������ �������
			 * �ִ�0��ʼ�����  ����ʵ�ʲ����з���ȷʵ���������´�0��ʼ�������� 
			 * ������������ԭ������ΪJVM�����ܱ�֤������⵽�жϵ��ٶ�.(
			 * ʵ�����Ƿǳ��Ŀ�� ���ǻ����к��ٵ��ӳ�  �����Ϊʲô�����ִ�0��ʼ�����ԭ��)
			 * ��ʵ���:
			 * 	index:203750
				index:203751
				do something before stop
				index:0
				index:1
				index:2
				����򵥵���˵������Ȼ������interrupt()���� ���ǲ�����ȷ���߳����ϵ�ͣ����
				���Կ��ܻ���ִ�к�С��һ��ʱ�� ����<strong>���ǿ���������֪���̵߳�״̬
				����ķ������ǵ����̵߳�isInterruptd()����   
				������Կ�run����ע�͵��ǲ��� (����Ҫ����Ӧ�ǳ��ߵĴ󲿷ֶ�������ʹ�õ� )ȥ��ע�͵Ļ� ��ô�������:
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
		 * ��Ȼth�߳���while(true){}����ʽ����
		 * ������Ϊ���������߳�ӵ��th�߳�  ���ǿ���ֱ��
		 * ����
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
	 * ������������  
	 * �����ǵ���interrupt������ʱ��  �ܹ��������л��߶���״̬���߳��ж�
	 */
	@Test
	public void testPrimeProducer(){
		PrimeProducer primeProducer = new PrimeProducer(new ArrayBlockingQueue<>(20));
		primeProducer.start();
		
		primeProducer.cancel();
		Log.info("stop....");
	}
	
	/**
	 * ��IO������ʱ�� ���ߵȴ������������� ��ô�ж�����ֻ�������̵߳��ж�״̬
	 * ����֮��û���κε�����  ������Щ����ִ�в����жϲ��������������߳�,����
	 * ʹ���������жϵ��ֶ���ֹͣ��Щ�߳�
	 * 
	 * 1.Java.io��ͬ����Socket I/O InputStream ��OutputStream �е�read��write����
	 * ��������Ӧ�ж� �������ǿ��Թرյײ���׽��� ������ʹ����Щ����������
	 * 2.Java.io�е�ͬ��IO
	 * 3.��ȡĳһ���� �ڻ�ȡ��������ʱ��  ������Ӧ�ж� 
	 * ����<strong>Lock.lockInterruptibly���� ,�÷��������ڵȴ�һ������ʱ������Ӧ�ж�</strong>
	 * 4....
	 * @see  <title>JAVA ���ʵս</title>
	 * 
	 */
	public static void main(String[] args) {
		
		Thread th = new Thread(){
			
			private ServerSocket ss;
			//��ΪSocket �Ķ������ܶ��жϽ�����Ӧ
			//����������������ͨ��interrupt()�����ж�����߳�
			//�������ǿ���ʹ��flag����ѭ���Ƿ�����
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
	 * ������ͨ������ѭ��  ���ǿ���ͨ���ж��̵߳ķ�ʽ ��������ж�
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
		 * ����֮���ܹ��ж��߳�
		 */
		th.interrupt();
	}
	
	/**
	 * ִ��һ��ȡ������
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
			//�ڵ���ǰ���ж��߳��Ƿ����ж�״̬
			while(!Thread.currentThread().isInterrupted()){
				//��BlockingQueue��Ϊ�����������Ժ�  �̶߳���ס  ����
				//���Ե���cancel()���߳̽����ж�  
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
