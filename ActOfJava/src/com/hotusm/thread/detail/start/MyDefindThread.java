package com.hotusm.thread.detail.start;

public class MyDefindThread extends Thread{

	@Override
	public void run() {
		Long id=this.getId();
		Log.info("线程("+id+")做了一些事情,然后结束了");
	}
	
	public static void main(String[] args) {
		new MyDefindThread().start();
	}
}
