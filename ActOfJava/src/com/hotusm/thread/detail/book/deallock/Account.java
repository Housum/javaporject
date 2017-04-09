package com.hotusm.thread.detail.book.deallock;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	
	private final Lock lock=new ReentrantLock();
	
	public void decrement(BigDecimal decimal){}
	public void increment(BigDecimal decimal){}
	
	public Lock getLock(){
		return this.lock;
	}
	
}
