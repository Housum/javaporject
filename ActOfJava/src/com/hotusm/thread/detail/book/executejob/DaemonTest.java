package com.hotusm.thread.detail.book.executejob;

import java.util.Scanner;

import com.hotusm.thread.detail.start.Log;

public class DaemonTest {
	
	public static void main(String[] args) {
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				Log.info("JVM exit!!!");
			}
		});
		
		Thread t=new Thread(){
			@Override
			public void run() {
				//��Ȼ����߳�һֱ��ִ��  ����ֻҪû�����̵߳�ʱ��   ��ô�ͻ��Ƴ�
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Log.info("thread number:"+Thread.currentThread().getName());
				}
			}
		};
		//����Ϊ�ػ��߳�  ������start֮ǰ����
		t.setDaemon(true);
		t.start();
		
		Scanner scanner=new Scanner(System.in);
		scanner.next();
	}
}
