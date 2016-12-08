package com.hotusm.thread.detail.book.monitor;

import com.hotusm.thread.detail.book.threadclose.Person;

/**
 * JAVA 监视器模式
 * 遵循JAVA监视模式的对象会把对象的可变对象全部进行封装  并由对象的内置锁来进行保护
 */
public class PrivateLock {

	private final Object mutex=new Object();
	Person person;
	void someMethod(){
		synchronized (mutex) {
			//在这里修改person的状态是线程安全的
		}
	}
}
