package com.hotusm.thread.detail.start;
/**
 * 
 *对象锁表示的是对资源的控制  这种时候有可能有多个线程具有
 *这个资源的钥匙  但是锁只有一个锁芯  所以只能够一哥钥匙进行
 *操作  在拔出钥匙以后 其他的线程才能够进入
 *
 *在JAVA中synchronized关键字可以加载很多位置。
 *您可以在一个方法定义上加synchronized关键字、也可以在方法体中加synchronized关键字、
 *还可以在static块中加synchronized关键字。如下的代码都是正确的：
 *
 *synchronized关键字加载在非静态方法上时： 
 *其代表的含义和synchronized(this){}的意义相同。即对所拥有这个方法的对象进行对象锁状态检查。

 *synchronized关键字加载在静态方法上时： 
 *其代表的含义和synchronized(Class.class)的意义相类似。即对所拥有这个方法的类的对象进行对
 *象锁状态检查（类本身也是一个对象）
 */
public class ThreadLock {
	
	private static final Object MUNTX=new Object();
	//这个是线程是不安全的  使用这个进行测试synchronized
	private static int num=0;
	public static void main(String[] args) {
		
		Thread a=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//while(true){
					synchronized(MUNTX){
						Log.info("a:"+num++);
					//}
				}
			}
		});
		
		Thread b=new Thread(new Runnable() {
			
			@Override
			public void run() {
				//while(true){
					synchronized(MUNTX){
						Log.info("b:"+num++);
					//}
				}
			}
		});
		a.start();
		b.start();
	}
}
