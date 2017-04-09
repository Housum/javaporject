package com.hotusm.thread.detail.start.jdkover5.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * Lock.lock()和Lock.tryLock()之间的区别
 * @see java.util.concurrent.locks.Lock.tryLock()
 * tryLock()如果前期能够获得锁的话 那么返回true  并且获得锁。
 * 或者返回false
 * 典型的用法：
 * <pre>
 *      Lock lock = ...;
 *      if (lock.tryLock()) {
 *          try {
 *              // manipulate protected state
 *          } finally {
 *              lock.unlock();
 *          }
 *      } else {
 *          // perform alternative actions
 *      }
 * </pre>
 * @see java.util.concurrent.locks.Lock.lock()
 * lock():尝试获得锁 如果没有获得锁  那么这个线程进行休眠，在其他地方释放锁的话  线程就能继续
 * 
 */
public class LockTest {
	
	/**
	 * 
	 * 将以前synchronized替换成ReentrantLock的方式
	 */
	@Test
	public void testReentrantLock(){
		
		final Lock lock=new ReentrantLock();
		
		new Thread(){

			@Override
			public void run() {
				lock.lock();
				Log.info("在做一件事...");
				try {
					//资源不释放  模拟时间停留
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}
			
			
		}.start();
		
		new Thread(){

			@Override
			public void run() {
				lock.lock();
				Log.info("在做另外一件事...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}
			
			
		}.start();
		
		synchronized (LockTest.class) {
			try {
				LockTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 这是一个读写锁  
	 * 在java.util.concurrent.locks包中，还提供了一个ReentrantReadWriteLock工具.
	 * 很显然，根据这个类的名字就明白了它的含义，即将多个线程对指定对象的读操作和写操作分开加锁。
	 * 
	 * 1.那么对象的写锁和读锁是怎么互相影响的呢？这个需要分开进行描述，首先我们来讨论一下，什么情况下线程可以获取某个对象的读锁：

		如果没有任何线程获取了对象的写锁。
		虽然有线程获取了对象的写锁，但是这个线程就是当前请求读锁的线程
		
		那么当前是否有线程获取了对象的读锁，并不会影响当前线程继续获取对象的读锁。什么情况下线程可以获取某个对象的写锁：
		
		没有任何线程获取了这个对象的读锁(在本线程获得写锁之后获得的读锁不算)
		没有任何线程获取了这个对象的写锁
	 */
	private static final Object LOCK_OBJECT=new Object();
	
	@Test
	public void teentrantReadWriteLock(){
		
		final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
		//两个线程可以同时获得读锁  并不会影响到对方
		new Thread(){
			@Override
			public void run() {
				ReadLock rl = lock.readLock();
				WriteLock wl = lock.writeLock();
				wl.lock();
				rl.lock();
				Log.info("一个线程正在执行");
//					try {
//						//经过测试如果加上这句话的话  会报java.lang.IllegalMonitorStateException
//						//估计是因为将资源锁住了  但是却调用了wait()方法将资源给分出去了  这样锁没有得到释放
//						
//						//LockTest.LOCK_OBJECT.wait(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				wl.unlock();
				rl.unlock();
				}
				
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				ReadLock rl = lock.readLock();
				WriteLock wl = lock.writeLock();
				//如果先调用上面的线程的话 那么线程会被锁在这里
				wl.lock();
				rl.lock();
				Log.info("另外一个线程正在执行");
//					try {
//						LockTest.LOCK_OBJECT.wait(5000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				wl.unlock();
				rl.unlock();
				}
				
		}.start();
		
		synchronized (LockTest.class) {
			try {
				LockTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	//这是一个使用Condition实现的多线程环境下的缓存区  
	//实现思想就是:  默认的容量是100 当其中的数量为0的时候  读线程被堵塞   当容器为100的时候   写线程被堵塞
	class BoundedBuffer {  
		   final Lock lock = new ReentrantLock();//锁对象  
		   final Condition notFull  = lock.newCondition();//写线程条件   
		   final Condition notEmpty = lock.newCondition();//读线程条件   
		  
		   final Object[] items = new Object[100];//缓存队列  
		   int putptr/*写索引*/, takeptr/*读索引*/, count/*队列中存在的数据个数*/;  
		  
		   public void put(Object x) throws InterruptedException {  
		     lock.lock();  
		     try {  
		       while (count == items.length)//如果队列满了   
		         notFull.await();//阻塞写线程  
		       items[putptr] = x;//赋值   
		       if (++putptr == items.length) putptr = 0;//如果写索引写到队列的最后一个位置了，那么置为0  
		       ++count;//个数++  
		       notEmpty.signal();//唤醒读线程  
		     } finally {  
		       lock.unlock();  
		     }  
		   }  
		  
		   public Object take() throws InterruptedException {  
		     lock.lock();  
		     try {  
		       while (count == 0)//如果队列为空  
		         notEmpty.await();//阻塞读线程  
		       Object x = items[takeptr];//取值   
		       if (++takeptr == items.length) takeptr = 0;//如果读索引读到队列的最后一个位置了，那么置为0  
		       --count;//个数--  
		       notFull.signal();//唤醒写线程  
		       return x;  
		     } finally {  
		       lock.unlock();  
		     }  
		   }   
		 } 
}

