package com.hotusm.thread.detail.book.deallock;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

import com.hotusm.thread.detail.start.Log;

/**
 * 顺序死锁
 * @see LeftRightDealLock
 * 因为这里有两个锁  而且是左右锁  所以会出现
 * 一个线程获得左边的锁  另外一个线程获得右边的锁
 * 那么就会出现死锁的问题  
 */
public class OrderDealLock {
	
	public static void main(String[] args) {
		final LeftRightDealLock lock=new LeftRightDealLock();
		new Thread(){
			public void run() {
				lock.left();
				lock.right();
			};
		}.start();
		
	}
	
	/**
	 * 动态死锁的示例:
	 * 因为这里会出现多个线程调用的情况 
	 * 因为from给to付款的时候  刚好to又给from付款的情况
	 * 那么就会出现死锁(和左右锁是相同的概念)
	 * 
	 * @param from   		付款方
	 * @param to     		收款方
	 * @param decimal		数额
	 * 
	 */
	@Deprecated
	public void transferMoney(Account from,Account to,BigDecimal decimal){
		//这里可以会出现死锁
		synchronized (from) {
			synchronized (to) {
				from.decrement(decimal);
				to.increment(decimal);
			}
		}
	}
	
	/**
	 * 1.在上面的基础进行修改 
	 * 使其基本上不会出现死锁的情况
	 * 2.但是如果下面的两个对象hashCode一样的情况,那么还是会出现死锁的问题
	 * 但是这种几率是非常非常低的  因为identityHashCode出现碰撞的情况很小
	 * 3.如果Accout包含唯一的,不可变的,并且具有可比性的键值,那么制定锁的顺序就更加的容易了
	 * 
	 * @param from
	 * @param to
	 * @param decimal
	 */
	public void transfer2Money(Account from,Account to,BigDecimal decimal){
		//使用System.identityHashCode(from)返回对象的hashCode  根据
		//hashCode进决定锁的顺序  这样的话  那么两个对象进来的时候  锁的顺序每一次都是一样的
		
		int fromHashCode=System.identityHashCode(from);
		int toHashCode=System.identityHashCode(from);
		if(fromHashCode>toHashCode){
			synchronized (from) {
				synchronized (to) {
					from.decrement(decimal);
					to.increment(decimal);
				}
			}
		}else{
			synchronized (to) {
				synchronized (from) {
					from.decrement(decimal);
					to.increment(decimal);
				}
			}
		}
	}
	/**
	 * 使用定时锁来解决死锁的问题
	 * 因为内置锁和一般的锁在获取不到锁的时候  
	 * 都会一直堵塞,但是我们可以收银Lock锁制定一个
	 * 规则,当一段时间以后还获取不到锁  那么
	 */
	@Test
	public void timerLock(){
		final ReentrantLock lock=new ReentrantLock();
		new Thread(){
			public void run() {
				try {
					if(lock.tryLock()){
						Thread.sleep(5000);
						lock.unlock();
					}
					Log.info("释放锁");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				//先将资源释放   让另外的一个线程先执行 这样就能获得锁
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//一直遍历 直到获得锁
				for(;;){
					
					if(lock.tryLock())
						break;
					else
						continue;
				}
				
				Log.info("获得锁");
				lock.unlock();
			};
		}.start();
		
		//另外的一种使用方法就是使用lock.tryLock(timeOut,TimeUint)在指定的时间内不能获取锁的话 那么就返回false 
		new Thread(){
			public void run() {
				try {
					long startTime=System.nanoTime();
					boolean isLock = lock.tryLock(2,TimeUnit.SECONDS);
					if(!isLock){
						long totalTime=System.nanoTime()-startTime;
						Log.info("等待时间:"+TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS)+"秒");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
/**
 * 锁顺序锁
 * @author Hotusm
 *
 */
class LeftRightDealLock{
	
	private Object LEFT_LOCK=new  Object();
	private Object RIGHT_LOCK=new  Object();
	
	public void left(){
		synchronized (LEFT_LOCK) {
			synchronized (RIGHT_LOCK) {
				
			}
			
		}
	}
	public void right(){
		synchronized (RIGHT_LOCK) {
			synchronized (LEFT_LOCK) {
			}
		}
	}
}
