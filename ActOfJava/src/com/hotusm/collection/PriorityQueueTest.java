package com.hotusm.collection;

import java.util.concurrent.PriorityBlockingQueue;


public class PriorityQueueTest {
	
	public static void main(String[] args) {
		//���ȼ�����
		PriorityBlockingQueue<Node> nodes=new PriorityBlockingQueue<Node>(4);
		System.out.println(nodes.offer(new Node("a")));
		System.out.println(nodes.offer(new Node("abcd")));
		System.out.println(nodes.offer(new Node("ab")));
		System.out.println(nodes.offer(new Node("abc")));
		
		int size=nodes.size();
		for(int i=0;i<size;i++){
			try {
				System.out.println(nodes.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Node implements Comparable<Node>{
	/**
	 * ����ַ�������һ���������  ��ô����Ϊ����ͬ��
	 * 
	 */
	private final String number;
	
	public Node(String number) {
		super();
		this.number = number;
	}

	@Override
	public int compareTo(Node o) {
		if(o==null){
			return -1;
		}
		if(number.length()<=o.number.length()){
			return -1;
		}else{
			return 1;
		}
		
	}
	
	@Override
	public String toString() {
		return "number:"+number;
	}
	
	
}
