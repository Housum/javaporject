package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.hotusm.thread.detail.start.Log;
/**
 * 2.һ����չ���̳߳أ����ڲ���ThreadPoolExecutor�е���չ����
 *	beforeExecute�����̳߳���Ҫ��ʼִ��ĳ�������ʱ��ע�ⲻ���������ȴ����е�ʱ���ǽ�Ҫ��ʼ��ʽ���̳߳���ִ�е�ʱ�򣩣��̳߳ػᴥ����������ĵ��á�
 *
 *	afterExecute�����̳߳������ĳһ�������ִ�к��̳߳ؾͻᴥ�����������
 *
 *	terminated�����̳߳ر���ִֹͣ�е�ʱ�򣬸÷����ͻᱻ���á�
 *
 *2.executor.submit��executor.execute�Ĳ�ͬ��
 *	executor.submit:
 *ʹ��submit�����ύ��ʵ����Runnable�ӿڵ����񣬽��ᱻ��װ�� �̳߳��ڲ�ʹ��Executors������callable����������RunnableAdapter�����С�
 *�����ǵ����������: @see java.util.concurrent.Executors.callable(Runnable, T)
 *  executor.execute:
 *���ַ�ʽ����˵��������з���  �ڲ�������ж�  �Ƿ��ܹ���ӻ��߲������   �׳��쳣
 *
 *3.��Ҫע���һ�����ִ��������������������߳����̳߳��е������߳�  �������ǿ��Կ���
 *<code>
 *  private final ThreadLocal<Long> startTime=new ThreadLocal<Long>();
 *	private final ThreadLocal<String> threadName=new ThreadLocal<String>();
 *</code>
 *��������������ͬһ���̹߳��������
 *
 */
public class ExtendThreadPoolExecutor extends ThreadPoolExecutor{
	
	/**
	 * ThreadLocalͬ�ڹ���һ���߳��еı���
	 */
	private final ThreadLocal<Long> startTime=new ThreadLocal<Long>();
	private final ThreadLocal<String> threadName=new ThreadLocal<String>();
	
	public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		startTime.set(System.nanoTime());
		threadName.set(t.getName());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		Long totalTime=System.nanoTime()-startTime.get();
		Log.info(threadName.get()+"thread  totalTIme:"+totalTime);
	}

	@Override
	protected void terminated() {
		Log.info("�߳�ֹͣ");
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExtendThreadPoolExecutor executor=new ExtendThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
		for(int i=0;i<10;i++){
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			
//			executor.submit(new Runnable() {
//				
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(2000);
//						Log.info("�߳�:"+Thread.currentThread().toString()+"��������");
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			});
		}
		/**
		 *          ����ָֹͣ�ע��ָֹͣ�����ȴ���Ҫʹ��awaitTermination���еȴ���
         *			ע�⣬�����������Ľ������̳߳صĹ���ԭ���̳߳����յ�shutdown��ָֹ���
         * 			�Ͳ����ٽ����ύ�����������ˣ����ۡ������̡߳����ȴ����д���ʲô����״̬��
		 */
		executor.shutdown();
		
		 // ����������ִ����ɺ���ֹ�̳߳ص�����
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
	}
	
	
}
