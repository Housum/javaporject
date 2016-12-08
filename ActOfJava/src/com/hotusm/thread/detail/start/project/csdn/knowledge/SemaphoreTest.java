package com.hotusm.thread.detail.start.project.csdn.knowledge;

import java.util.concurrent.Semaphore;

import com.hotusm.thread.detail.start.Log;
/**
 * @link http://blog.csdn.net/yinwenjie/article/details/50659540
 * 
 *   1.semaphore：信号旗 和synchronize和lock是相似的
 * 在多个线程进行访问一个资源的时候  那么我们会先进行证书的申请
 * 如果没有申请到证书的话 那么就会一直堵塞 如果申请到证书  操作完
 * 业务的时候  就能将证书归还  这个过程 其实就是一个锁的过程
 *   2.但是有一点不同的时候 我们可以看到Semaphore构造函数有
 * 两个参数  第一个是int型的  意思是说这里有可以有多个信号旗 也就是
 * 说多个线程可以同时申请到指定的型号旗
 *   3.各个方法的作用
 *     ①void acquire()：从此信号量获取一个许可，在Semaphore能够提供一个许可前，当前线程将一直阻塞等待。如果在等待过程中，当前线程收到了interrupt信号，那么将抛出InterruptedException异常
 *     ②void acquire(permits)：从此信号量获取permits个许可，在Semaphore能够提供permits个许可前，当前线程将一直阻塞等待。如果在等待过程中，当前线程收到了interrupt信号，那么将抛出InterruptedException异常。
 *     ③void acquireUninterruptibly()：从此信号量获取一个许可，在Semaphore能够提供一个许可前，当前线程将一直阻塞等待。使用这个方法获取许可时，不会受到线程interrupt信号的影响。
 *     4void acquireUninterruptibly(permits)：从此信号量获取permits个许可，在Semaphore能够提供permits个许可前，当前线程将一直阻塞等待。使用这个方法获取许可时，不会受到线程interrupt信号的影响
 *     ⑤boolean tryAcquire()：从此信号量获取一个许可，如果无法获取，线程并不会阻塞在这里。如果获取到了许可，则返回true，其他情况返回false。
 *     ⑥boolean tryAcquire(permits)：从此信号量获取permits个许可，如果无法获取，线程并不会阻塞在这里。如果获取到了许可，则返回true，其他情况返回false。
 *     7.boolean tryAcquire(int permits, long timeout, TimeUnit unit)：从此信号量获取permits个许可，如果无法获取，则当前线程等待设定的时间。如果超过等待时间后，还是没有拿到许可，则解除等待继续执行。如果获取到了许可，则返回true，其他情况返回false。
 *   
 *   证书状态：
		int availablePermits()：返回此信号量中当前可用的许可数。
		int getQueueLength()：返回正在等待获取的线程的估计数目。该值仅是估计的数字，因为在此方法遍历内部数据结构的同时，线程的数目可能动态地变化。此方法用于监视系统状态，不用于同步控制。
		boolean hasQueuedThreads()：查询是否有线程正在等待获取。注意，因为同时可能发生取消，所以返回 true 并不保证有其他线程等待获取许可。此方法主要用于监视系统状态。
		boolean isFair()：如果此信号量的公平设置为 true，则返回 true。
		释放/返还证书：
		void release()：释放一个许可，将其返回给信号量。最好将这个方法的调用，放置在finally程序块中执行。
		void release(permits)：释放给定数目的许可，将其返回到信号量。最好将这个方法的调用，放置在finally程序块中执行。
		fair：公平与非公平
		Semaphore一共有两个构造函数，分别是：Semaphore(int permits)和Semaphore(int permits, boolean fair)；permits是指由Semaphore信号量控制的“证书”数量。fair参数是设置这个信号量对象的工作方式。
		当fair参数为true时，信号量将以“公平方式”运行。即首先申请证书，并进入阻塞状态的线程，将有权利首先获取到证书；当fair参数为false时，信号量对象将不会保证“先来先得”。默认情况下，Semaphore采用“非公平”模式运行。
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		Semaphore semaphore=new Semaphore(5, true);
		for(int i=0;i<10;i++){
			new Thread(new SemaphoreRunnableNonfair(i,semaphore)).start();
		}
		
		synchronized (SemaphoreTest.class) {
			try {
				SemaphoreTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SemaphoreRunnableNonfair implements Runnable{

	private int index;
	private Semaphore semaphore;
	
	public SemaphoreRunnableNonfair(int index, Semaphore semaphore) {
		super();
		this.index = index;
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		Log.info("线程"+this.index+"等待信号....");
		try {
			/**
			 * 申请证书  如果没有申请到证书的话 那么就会一直的堵塞  
			 */
			this.semaphore.acquire();
			Log.info("index为"+this.index+"的线程,获得信号,开始处理业务");
			//停止5秒钟的时间   
			//synchronized (this) {
				this.wait(5000);
			//}
		} catch (Exception e) {
			
		}finally{
			//不管最后的结果是怎样  都应该归还证书  这样其他的人才能够得到证书
			this.semaphore.release();
			Log.info("index为"+this.index+"的线程,释放信号");		
		}
	}
}
