package com.hotusm.collection;

import java.util.concurrent.SynchronousQueue;
/**
 * SynchronousQueue��û��������  ʵ�ַ�ʽΪ�����ж�����߳�  
 * �����ֵput��ȥ  ��ô����Ҫ�������߳�����ȡ
 * 
 */
public class SynchronizedQueueTest {
	
	public static void main(String[] args) {
		
		int cpuNumber = Runtime.getRuntime().availableProcessors();
		System.out.println(cpuNumber);
		final SynchronousQueue<Node> nodes=new SynchronousQueue<Node>();
		
		
		new Thread(){
			public void run() {
				try {
					//��item���ý�ȥ ��ô����߳̾ͻ�һֱ����  ֱ���������߳̽�item��take��ȥ
					nodes.put(new Node("node"));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				try {
					Node node = nodes.take();
					System.out.println(node.toString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
	}
	
	
}
