package com.hotusm.thread;

/**
 * 具体的方法在调用的时候  
 * 才被构建出来  
 *
 */
public class SafeTest {
	
	int tempNumber=0;
	
	public static void main(String[] args) {
		
		final SafeTest st=new SafeTest();
		
		new Thread(){
			public void run() {
				st.numberStatus(10,true);
			};
		}.start();
		
		new Thread(){
			public void run() {
				st.numberStatus(20, false);
			};
		}.start();
		
		synchronized (SafeTest.class) {
			try {
				SafeTest.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void numberStatus(int number,boolean wait){
		System.out.println("tempNumber: "+tempNumber);
		if(wait){
			synchronized (this) {
				try {
					this.wait(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else{
			tempNumber=10;
		}
		System.out.println(Thread.currentThread()+" number :"+number);
		System.out.println("tempNumber: "+tempNumber);
		
	}
	
}
