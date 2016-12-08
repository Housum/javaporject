package com.hotusm.thread.detail.start.notifythread;

import com.hotusm.thread.detail.start.Log;


/**
 * 
 *notify()就是唤醒一个被对象钥匙给wait()的线程,如果有多个的线程在等待,那么其中的一个就会被唤醒(这个时候还是就绪状态)
 *notifyAll()和上面那个方法有些相似  但是这个方法是唤醒所有被对象钥匙给wait()的线程
 *
 *下面演示了这个效果:
 *	三个子线程都使用同一个对象的wait()方法进行挂起.然后在主线程里面调用这个钥匙的notify()方法  其中的一个线程能够
 *  打印出语句出来  但是其他的两个线程并不能.或者我们调用notifyAll()方法.这个时候就能够看到三个线程的内容都被打印出来了
 *  
 */
public class ParentNotifyThread implements Runnable{
	
	public static final Object MONITOR=new Object();
	@Override
	public void run() {
		for(int i=0;i<3;i++){
			new Thread(new ChildNotifyThread()).start();
		}
		
		//这个方法先打一个DEBUG  将线程先在这里挂起  让上面的三个线程执行完(因为里面有一个ParentNotifyThread.MONITOR.wait()非常重要)
		synchronized (MONITOR) {
			MONITOR.notify();
			/*
			 * console:
			 * 	线程 14启动成功,准备进入等待状态
				线程 15启动成功,准备进入等待状态
				线程 16启动成功,准备进入等待状态
				(这个让debug继续执行)
				线程14被唤醒!
			 * */
			//MONITOR.notifyAll();
			/*
			 *console:
			 *	线程 14启动成功,准备进入等待状态
				线程 16启动成功,准备进入等待状态
				线程 15启动成功,准备进入等待状态
				(这个让debug继续执行)
				线程15被唤醒!
				线程14被唤醒!
				线程16被唤醒!
			 * 
			 */
		}
		
		
		//这里就是不让程序退出
		synchronized (ParentNotifyThread.class) {
			try {
				ParentNotifyThread.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) 
	{
		new Thread(new ParentNotifyThread()).start();
	}
}
class ChildNotifyThread implements Runnable{

	@Override
	public void run() {
		Thread currentThread=Thread.currentThread();
		long id = currentThread.getId();
		
		Log.info("线程 "+id+"启动成功,准备进入等待状态");
		
		//这里具有了ParentNotifyThread.MONITOR这个‘钥匙’
		synchronized(ParentNotifyThread.MONITOR){
			try {
				ParentNotifyThread.MONITOR.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Log.error("线程"+id+"出现问题 ");
			}
		}
		Log.info("线程"+id+"被唤醒!");
	}
}

