package com.hotusm.thread.detail.start;

public class MyDefindRunnable implements Runnable{

	@Override
	public void run() {
		   //　获取当前线程的ＩＤ
        long threadId = Thread.currentThread().getId();
        Log.info("线程（" + threadId + "）做了一些事情，然后结束了。");
	}
	
	public static void main(String[] args) {
		new Thread(new MyDefindRunnable()).start();
	}
}
