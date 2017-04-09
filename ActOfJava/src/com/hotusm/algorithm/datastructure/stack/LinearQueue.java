package com.hotusm.algorithm.datastructure.stack;

public class LinearQueue{

	private Object[] arr;
	private int first;
	private int last;
	private int capacity;
	
	public LinearQueue(){
		arr=new Object[16];
		first=last=0;
		capacity=16;
	}
	public LinearQueue(int capacity){
		arr=new Object[capacity];
		this.capacity=capacity;
	}
	public void offer(Object obj){
		if(last%capacity==first%capacity&&last!=first){
			throw new RuntimeException("full");
		}
		arr[last%capacity]=obj;
		last++;
	}
	public Object poll(){
		if(last==first){
			throw new RuntimeException("empty");
		}
		int index=first%capacity;
		first++;
		return arr[index];
	}
	
	public static void main(String[] args) {
		LinearQueue lQueue=new LinearQueue(3);
		lQueue.offer("1");
		lQueue.offer("2");
		lQueue.offer("3");
		lQueue.offer("3");
		System.out.println(lQueue.poll());
		System.out.println(lQueue.poll());
		System.out.println(lQueue.poll());
		System.out.println(lQueue.poll());
	}
}
