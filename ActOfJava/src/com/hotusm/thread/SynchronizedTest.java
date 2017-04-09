package com.hotusm.thread;

import com.hotusm.thread.detail.start.Log;


/**
 * 
 * 
 * @author Hotusm
 * @since 2016-03-13
 * 资源共享
 * 
 * 看下面 重点!!!
 * 
 */
public class SynchronizedTest {
	
	public static void main(String[] args) {
//		IntGen intGen=new IntGen();
//		new Provider(intGen);
//		new Provider(intGen);
//		new Provider(intGen);
//		new Provider(intGen);
//		new Provider(intGen);
		
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					/**
					 * 如果我们不加下面的synchronized 的话  那么就会抛出java.lang.IllegalMonitorStateException
					 * 这个异常出现原因就是说在没有监视器的情况下  调用wait()  但是如果我们加了synchronized的话 那么就
					 * 能够保证能够得到监视器
					 */
					synchronized (this) {
						try {
							Log.info("线程在运行");
							this.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();;
		}
	}
}
class IntGen{
	
	private int num=0;
	/**
	 * 
	 * 对方法进行加锁,那么每一次只能有一个线程能够访问
	 * 这样就不会出现相同的数据
	 * @return
	 */
	public synchronized int next(){
		num++;
		return num;
	}
}
class Provider extends Thread{
	
	private IntGen intGen;

	public Provider(IntGen intGen) {
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






