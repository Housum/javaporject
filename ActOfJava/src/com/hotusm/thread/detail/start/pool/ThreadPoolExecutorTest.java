package com.hotusm.thread.detail.start.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.hotusm.thread.detail.start.Log;

/**
 * 详细的讲解 @link ThreadPoolExecutor的使用
 * @author Hotusm
 * 对于常用的构造函数是:
 * public ThreadPoolExecutor(int corePoolSize,
 *                            int maximumPoolSize,
 *                             long keepAliveTime,
 *                             TimeUnit unit,
 *                            BlockingQueue<Runnable> workQueue)
 *其中最复杂 的是:
 *public ThreadPoolExecutor(int corePoolSize,
 *                             int maximumPoolSize,
 *                             long keepAliveTime,
 *                             TimeUnit unit,
 *                             BlockingQueue<Runnable> workQueue,
 *                             ThreadFactory threadFactory,
 *                             RejectedExecutionHandler handler)
 */
public class ThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		ThreadPoolExecutor executor=new ThreadPoolExecutor(5, 10, 1, TimeUnit.MINUTES, new SynchronousQueue<Runnable>(),new TestThreadFactory(),new RejectedExecutionHandlerImpl());
		//初始化的时候 就分类corePoolSize个任务
		executor.prestartCoreThread();
		for(int i=0;i<12;i++){
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					Log.info(Thread.currentThread().getId()+"正在运行");
				}
			});
		}
		
		Thread currentThread = Thread.currentThread();
	}
}
/**
 * 在特殊的时候我们也可以自己实现ThreadFactory
 *
 */
class TestThreadFactory implements ThreadFactory{
	
	public static int index=0;

	@Override
	public Thread newThread(Runnable r) {
		//这里可以在创建线程的时候做一些逻辑
		synchronized (ThreadPoolExecutorTest.class) {
			Log.info("这是第"+index+++"线程");
		}
		return new Thread();
	}
}
/**
 * 
 * 在使用ThreadPoolExecutor线程池的时候，需要指定一个实现了BlockingQueue接口的任务等待队列。
 * 在ThreadPoolExecutor线程池的API文档中，一共推荐了三种等待队列，它们是：SynchronousQueue,
 * LinkedBlockingQueue和ArrayBlockingQueue；但通过观察BlockingQueue接口的实现情况,
 * 您可以发现，能够直接使用的等待队列还有：PriorityBlockingQueue
 * LinkedBlockingDeque和LinkedTransferQueue。
 *
 */

/**
 * SynchronousQueue：
 *
 */
class SynchronousQueueTest{
	
	public void queue() throws InterruptedException{
		SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
		
		// 不要使用add，因为这个队列内部没有任何容量，所以会抛出异常“IllegalStateException”
		// queue.add(new Object());
		// 操作线程会在这里被阻塞，直到有其他操作线程取走这个对象
		queue.put(new Object());
	}
}
/**
 * ArrayBlockingQueue：
 *一个由数组支持的有界阻塞队列。此队列按 FIFO（先进先出）原则对元素进行排序。
 *新元素插入到队列的尾部，队列获取操作则是从队列头部开始获得元素。
 *这是一个典型的“有界缓存区”，固定大小的数组在其中保持生产者插入的元素和使用者提取的元素。
 *一旦创建了这样的缓存区，就不能再增加其容量。试图向已满队列中放入元素会导致操作受阻塞；
 *试图从空队列中提取元素将导致类似阻塞。
 */
class ArrayBlockingQueueTest{
	
	public void arrayQueue() throws InterruptedException{
		// 我们创建了一个ArrayBlockingQueue，并且设置队列空间为2
		ArrayBlockingQueue<Object> arrayQueue = new ArrayBlockingQueue<Object>(2);
		// 插入第一个对象
		arrayQueue.put(new Object());
		// 插入第二个对象
		arrayQueue.put(new Object());
		// 插入第三个对象时，这个操作线程就会被阻塞(等待前面的队列出去)。
		arrayQueue.put(new Object());
		// 请不要使用add操作，和SynchronousQueue的add操作一样，它们都使用了AbstractQueue中的add实现
	}
}
/**
 * LinkedBlockingQueue:
 * LinkedBlockingQueue是我们在ThreadPoolExecutor线程池中常应用的等待队列。
 * 它可以指定容量也可以不指定容量。由于它具有“无限容量”的特性，所以我还是将它归入了无限队列的范畴（实际上任何无限容量的队列/栈都是有容量的，这个容量就是Integer.MAX_VALUE）。
 * LinkedBlockingQueue的实现是基于链表结构，而不是类似ArrayBlockingQueue那样的数组。但实际使用过程中，您不需要关心它的内部实现，
 * 如果您指定了LinkedBlockingQueue的容量大小，
 * 那么它反映出来的使用特性就和ArrayBlockingQueue类似了
 *
 */
class LinkedBlockingQueueTest{
	
	public void linkedBlockQueue() throws InterruptedException{
		LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>(2);
		linkedQueue.put(new Object());
		// 插入第二个对象
		linkedQueue.put(new Object());
		// 插入第三个对象时，这个操作线程就会被阻塞。(这里的作用就是和ArrayBlockingQueue是一样的)
		linkedQueue.put(new Object());

		//=======================================

		// 或者如下使用：
		LinkedBlockingQueue<Object> linkedQueue1 = new LinkedBlockingQueue<Object>();
		linkedQueue1.put(new Object());
		// 插入第二个对象
		linkedQueue1.put(new Object());
		// 插入第N个对象时，都不会阻塞
		linkedQueue1.put(new Object());
	}
}
/**
 * LinkedBlockingDeque双端队列
 * LinkedBlockingDeque是一个基于链表的双端队列。
 * LinkedBlockingQueue的内部结构决定了它只能从队列尾部插入,从队列头部取出元素；
 * 但是LinkedBlockingDeque既可以从尾部插入/取出元素，还可以从头部插入元素/取出元素。
 */
class LinkedBlockingDequeTest{
	
	public void linkedBlockingDeque(){
		PriorityBlockingQueue<TempObject> priorityQueue = new PriorityBlockingQueue<TempObject>();
		priorityQueue.put(new TempObject(-5));
		priorityQueue.put(new TempObject(5));
		priorityQueue.put(new TempObject(-1));
		priorityQueue.put(new TempObject(1));

		// 第一个元素是5
		// 实际上在还没有执行priorityQueue.poll()语句的时候，队列中的第二个元素不一定是1
		TempObject targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
		// 第二个元素是1
		targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
		// 第三个元素是-1
		targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
		// 第四个元素是-5
		targetTempObject = priorityQueue.poll();
		System.out.println("tempObject.index = " + targetTempObject.getIndex());
	}
}
/**
 * LinkedTransferQueue也是一个无限队列，它除了具有一般队列的操作特性外（先进先出），还具有一个阻塞特性：LinkedTransferQueue可以由一对生产者/消费者线程进行操作，当消费者将一个新的元素插入队列后，消费者线程将会一直等待，直到某一个消费者线程将这个元素取走，反之亦然。
 * @author Hotusm
 * 具体的例子可以看:
 * @link http://blog.csdn.net/yinwenjie/article/details/50577325
 */
class LinkedTransferQueueTest{
	
	public void linkedtransferqueue(){
		
	}
}
class TempObject implements Comparable<TempObject>{
	
	private int index;
	
	public TempObject(int index){
		this.index=index;
	}
	//如果是负数  那么就是小于给定的对象  0就是等于  整数是大于
	@Override
	public int compareTo(TempObject o) {
		return o.getIndex()-index;
	}
	public int getIndex() {
		return index;
	}
}
/**
 * 拒绝任务:
 * 接下里这部分是讲解最后以后一个参数RejectedExecutionHandler的
 * 在ThreadPoolExecutor线程池中还有一个重要的接口：RejectedExecutionHandler。
 * 当提交给线程池的某一个新任务无法直接被线程池中“核心线程”直接处理，又无法加入等待队列，也无法创建新的线程执行；
 * 又或者线程池已经调用shutdown()方法停止了工作；又或者线程池不是处于正常的工作状态;
 * 这时候ThreadPoolExecutor线程池会拒绝处理这个任务，
 * 触发您创建ThreadPoolExecutor线程池时定义的RejectedExecutionHandler接口的实现：
 * 
 * 如果我们在构造函数中没有指明这个参数的话  那么就会默认的帮我们创建一个接口的实现
 * @see java.util.concurrent.ThreadPoolExecutor.AbortPolicy
 * 
 */
/**
 * 下面这个是自己继承的一个异常处理类
 * @author Hotusm
 *
 */
class RejectedExecutionHandlerImpl implements RejectedExecutionHandler{

	private AtomicInteger index=new AtomicInteger(0); 
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		Log.error("线程:"+r.toString()+"被拒绝执行 有可能是以下的几种可能:"
				+ "当提交给线程池的某一个新任务无法直接被线程池中“核心线程”直接处理，又无法加入等待队列，也无法创建新的线程执行"
				+ "又或者线程池已经调用shutdown()方法停止了工作；又或者线程池不是处于正常的工作状态");
		Log.error("这是第"+index.incrementAndGet()+"被拒绝的线程");
	}
}
/**
 * 默认只有四种:
 *  1.CallerRunsPolicy:这个拒绝处理器，将直接运行这个任务的run方法。但是，请注意并不是在ThreadPoolExecutor线程池中的线程中运行
 *  而是直接调用这个任务实现的run方法。
 *  @see java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
 *  2.AbortPolicy
 *  这个处理器，在任务被拒绝后会创建一个RejectedExecutionException异常并抛出。这个处理过程也是ThreadPoolExecutor线程池默认的RejectedExecutionHandler实现：
 *  @see java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
 *  3.DiscardPolicy
 *  DiscardPolicy处理器，将会默默丢弃这个被拒绝的任务，不会抛出异常，也不会通过其他方式执行这个任务的任何一个方法，更不会出现任何的日志提示
 *  @see java.util.concurrent.ThreadPoolExecutor.DiscardPolicy
 *  4.DiscardOldestPolicy
 *  这个处理器很有意思。它会检查当前ThreadPoolExecutor线程池的等待队列。并调用队列的poll()方法，将当前处于等待队列列头的等待任务强行取出，然后再试图将当前被拒绝的任务提交到线程池执行：
 *  @see java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy
 */
