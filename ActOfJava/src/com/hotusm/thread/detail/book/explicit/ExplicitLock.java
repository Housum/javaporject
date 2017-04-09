package com.hotusm.thread.detail.book.explicit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.hotusm.thread.detail.book.deallock.Account;
import com.hotusm.thread.detail.book.explicit.BlockingMap.SynLock;
import com.hotusm.thread.detail.start.Log;

/**
 * 显式锁: 
 *  在JDK1.5之前只有synchronized 或者volatile
 *  在JDK1.5之后加了显式锁 增加了无条件,可轮询,定时的以及可中断的
 *  锁获取操作  
 *  @Lock  @ReentrantLock 
 *  显式锁必须显式的将锁释放 不然很容易的出现崩溃
 *   
 */
public class ExplicitLock {

	private final Lock jobLock =new ReentrantLock();
	
	/**
	 * 默认的情况下 为不公平锁  我们可以在构造中设置是否为公平锁
	 */
	private final Lock systemLock=new ReentrantLock(true);
	/**
	 * 采用显式锁 可轮询  而不会堵塞
	 * 在规定的时间内进行轮询   如果时间超过的话 那么就返回操作失败
	 * 
	 * @param from
	 * @param to
	 * @param decimal
	 */
	public boolean  transforMoney(Account from,Account to,BigDecimal decimal,long timeout,TimeUnit unit){
		
		long stopTime=System.nanoTime()+unit.toNanos(timeout);
		
		while(true){
			if(from.getLock().tryLock()){
				try {
					if(to.getLock().tryLock()){
						try {
							from.decrement(decimal);
							to.increment(decimal);
						} finally{
							to.getLock().unlock();
						}
					}
				} finally{
					from.getLock().unlock();
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(stopTime>System.nanoTime()){
				return false;
			}
		}
	}
	/**
	 * 可以设置锁的等待时间 如果在设定的时间内
	 * 没有获得锁的话 那么就返回false
	 * @param job
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public boolean tryToWorkOut(Job job,long timeout,TimeUnit timeUnit){
		try {
			if(jobLock.tryLock(timeout, timeUnit))
				return false;
			else{
				try {
					job.doWork();
					return true;
				}finally{
					jobLock.unlock();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * lockInterruptibly能够响应中断
	 * 
	 */
	public void releaseSys(){
		try {
			try {
				systemLock.lockInterruptibly();
				Log.info("正在释放系统的资源");
				Thread.sleep(3000);
			} finally{
				systemLock.unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		final BlockingMap<Integer,Integer> map=new BlockingMap<Integer,Integer>();
//		new Thread(){
//			public void run() {
//				for(int i=0;;i++){
//					try {
//						Thread.sleep(50);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					map.put(i, i);
//				}
//			};
//		}.start();
//		
//		new Thread(){
//			public void run() {
//				while(true){
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println(map.size());
//				}
//			};
//		}.start();
		
		final SynLock lock = new BlockingMap.SynLock(3);
		
		for(int i=0;i<3;i++){
			Log.info("第"+i+"线程");
			new Thread(){
				public void run() {
					lock.acquire();
				};
			}.start();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/**
 * 这是自己封装的一个堵塞map
 * @author Hotusm
 *
 * @param <K>
 * @param <V>
 */
class BlockingMap<K,V> implements Map<K, V>,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final static int DEFAULT_CAPACITY=32;
	private final Lock putLock=new ReentrantLock();
	private final Lock takeLock=new ReentrantLock();
	private final Condition fullLock=putLock.newCondition();
	private final Condition nullLock=takeLock.newCondition();
	private final AtomicInteger count=new AtomicInteger(0);
	
	private int capacity=DEFAULT_CAPACITY;
	private Map<K,V> maps=new ConcurrentHashMap<>();
	
	public BlockingMap(){}
	public BlockingMap(int capacity){
		this.capacity=capacity;
	}
	
	public V put(K key, V value){
		V v=null;
		putLock.lock();
		try {
			if(count.get()>=this.capacity){
				try {
					fullLock.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				fullLock.signal();
				count.incrementAndGet();
				v=maps.put(key, value);
			}
		} finally {
			putLock.unlock();
		}
		
		if(count.get()>0){
			signalNullLock();
		}
		return v;
	}
	@Override
	public int size() {
		return maps.size();
	}
	@Override
	public boolean isEmpty() {
		return maps.isEmpty();
	}
	@Override
	public boolean containsKey(Object key) {
		return maps.containsKey(key);
	}
	@Override
	public boolean containsValue(Object value) {
		return maps.containsValue(value);
	}
	@Override
	public V get(Object key) {
		V v=null;
		takeLock.lock();
		try {
			if(count.get()>0){
				nullLock.signal();
				count.decrementAndGet();
				v=maps.remove(key);
			} else
				try {
					nullLock.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		} finally {
			takeLock.unlock();
		}
		if(count.get()<=0){
			signalFullLock();
		}
		return v;
	}
	
	private void signalFullLock(){
		putLock.lock();
		try {
			fullLock.signal();
		} finally {
			putLock.unlock();
		}
	}
	private void signalNullLock(){
		takeLock.lock();
		try {
			nullLock.signal();
		} finally {
			takeLock.unlock();
		}
	}
	@Override
	public V remove(Object key) {
		takeLock.lock();
		try {
			if(this.size()>0){
				fullLock.signal();
				return maps.remove(key);
			}else{
				try {
					nullLock.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			takeLock.unlock();
		}
		if(maps.size()<=0){
			fullLock.signal();
		}
		return null;
	}
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		
	}
	@Override
	public void clear() {
		maps.clear();
		
	}
	@Override
	public Set<K> keySet() {
		return maps.keySet();
	}
	@Override
	public Collection<V> values() {
		return maps.values();
	}
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return maps.entrySet();
	}
	
	/**
	 *
	 * 使用内置锁和条件队列实现Semaphore
	 */
	public static class SynLock{
		
		private AtomicInteger count=new AtomicInteger(0);
		
		private final int number;
		
		public SynLock(int number){
			this.number=number;
		}
		
		public void acquire(){
			synchronized (this) {
				
				count.incrementAndGet();
				if(count.get()==number)
					this.notifyAll();
				else
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	public static class CountDownLatchExtend{
		
	}
}