package com.hotusm.thread.detail.start;

public class MyDefindThread extends Thread{

	@Override
	public void run() {
		Long id=this.getId();
		Log.info("�߳�("+id+")����һЩ����,Ȼ�������");
	}
	
	public static void main(String[] args) {
		new MyDefindThread().start();
	}
}
