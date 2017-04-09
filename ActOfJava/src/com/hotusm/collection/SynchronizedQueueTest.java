package com.hotusm.collection;

import java.util.concurrent.SynchronousQueue;
/**
 * SynchronousQueue是没有容量的  实现方式为其中有多个的线程  
 * 如果将值put进去  那么就需要其他的线程来获取
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
					//将item设置进去 那么这个线程就会一直堵塞  直到有其他线程将item给take出去
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
