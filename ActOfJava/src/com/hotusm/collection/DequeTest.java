package com.hotusm.collection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * ˫�˶���  �ʺϹ�����ȡ�ķ�ʽ  
 *
 */
public class DequeTest {
	
	public static void main(String[] args) {
		final AtomicInteger index=new AtomicInteger(0);
		final Deque<Node> deques=new ArrayDeque<Node>();
		final Deque<Node> deques2=new ArrayDeque<Node>();
		//producer
		new Thread(){
			public void run() {
				synchronized (this) {
					for(;;){
						int number = index.getAndIncrement();
						if(number%5!=0){
							deques.add(new Node(""+number));
						}else{
							deques2.add(new Node(""+number));
						}						
					}
				}
			};
		}.start();
		
		//customer1
		new Thread(){
			public void run() {
				while(true){
					synchronized (this) {
						if(deques.isEmpty()){
							continue;
						}
						Node node = deques.pollFirst();
						System.out.println("customer1"+node);
					}
				}
			};
		}.start();
		
		//customer2
		new Thread(){
			public void run() {
				while(true){
					synchronized (this) {
						//�������2��Ϊ�յĻ� ��ô�ʹӶ���1�л�ȡԪ��  
						if(deques2.isEmpty()){
							if(!deques.isEmpty()){
								Node last = deques.pollLast();
								if(last!=null){
									System.out.println("last"+last);
								}
							}
						}else{
							Node node = deques2.pollFirst();
							System.out.println("customer2"+node);
						}
					}
				}
			};
		}.start();
		
	}
	
	
}
