package com.hotusm.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 
 * @author Hotusm
 * Lock是在JAVA SE5中显式定义的互斥对象
 * Lock的功能是synchronized一样的功能,但是不一样的地方在于
 * 必须被显式的创建，锁定和释放
 */
public class LockTest {
	
	public static void main(String[] args) {
		IntGen1 intGen=new IntGen1();
		new Provider1(intGen);
		new Provider1(intGen);
		new Provider1(intGen);
		new Provider1(intGen);
		new Provider1(intGen);
	}
}
class IntGen1{
	/*
	 * 必须显式的创建互斥对象,并且显式的锁定和释放
	 * 
	 * */
	private Lock lock =new ReentrantLock();
	private int num=0;
	
	public int next(){
		//显式的锁定资源
		lock.lock();
		try {
			num++;
			return num;
		} finally{
			//显式的释放资源
			lock.unlock();
		}
		
	}
}
class Provider1 extends Thread{
	
	private IntGen1 intGen;

	public Provider1(IntGen1 intGen) {
		super();
		this.intGen = intGen;
		start();
	}

	@Override
	public void run() {
		while(true){
			System.out.println(intGen.next());
		}
	}
	
}