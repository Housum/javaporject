package com.hotusm.algorithm.datastructure.stack;

public class Stack {
	
	int[] arr=new int[10];
	private int index=0;
	
	public int pop(){
		if(index<0){
			return -0xFFFF;
		}
		return arr[index--];
	}
	public boolean hasMore(){
		return index>=0;
	}
	public void push(int num){
		if(index>9){
			return;
		}
		if(index==9){
			arr[index]=num;
		}else{
			arr[index++]=num;
		}
		
	}
	
	public static void main(String[] args) {
		
		Stack stack=new Stack();
		
		for(int i=0;i<10;i++){
			stack.push(i);
		}
		
		while(stack.hasMore()){
			System.out.println(stack.pop());
		}
		
		System.out.println(stack.pop());
		
	}

}
