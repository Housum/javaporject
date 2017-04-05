package com.hotusm.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 队列  @link http://blog.csdn.net/ydj7501603/article/details/17246949
 * 
 *@author Hotusm
 *offer(...) 向队列中加入数据 不会导致线程的堵塞 成功会返回true否则false
 *put(...)   向队列中加入数据 如果队列满了的话 那么就会被堵塞
 *poll(...)  从队列中获取数据  如果失败返回null
 *take(...)  从队列中获取数据 会导致线程堵塞
 *drainTo 一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数）， 
　　　　通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁,还能够进行批量的替换
  @see java.util.concurrent.ArrayBlockingQueue<E>
  	基于数组的阻塞队列实现，在ArrayBlockingQueue内部，维护了一个定长数组
  @see java.util.concurrent.LinkedBlockingQueue<E>
         基于链表的阻塞队列
  @see java.util.concurrent.DelayQueue<E>
         延迟队列      DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue是一个没有大小限制的队列
   @link http://www.cnblogs.com/jobs/archive/2007/04/27/730255.html
  @see java.util.concurrent.PriorityBlockingQueue<E>
  	优先级队列
  @see java.util.concurrent.SynchronousQueue<E>
          没有中间的缓存区  消费者和生产者进行直接的交流 同时也没有容量
 */
public class QueueTest {
	
	public static void main(String[] args) {
		
		//堵塞队列  
		final BlockingQueue<Integer> q=new ArrayBlockingQueue<Integer>(3);
		final AtomicInteger number=new AtomicInteger(0);
		new Thread(){
			public void run() {
				while(true){
					Integer integer;
						try {
							integer = q.take();
							System.out.println("消费者获取:"+integer);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				
				while(true){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//---------------------------
							try {
								q.put(number.getAndIncrement());
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("生成者生产数字");
						}
			};
		}.start();
	}
	
	public void listTypeOfQueue(){
		
		/**/
		BlockingQueue<?> queue=new ArrayBlockingQueue<>(10);
				
	    queue=new LinkedBlockingQueue<>();
		
		queue=new SynchronousQueue<>();
		
		queue=new PriorityBlockingQueue<>();
		
		
	}
}
