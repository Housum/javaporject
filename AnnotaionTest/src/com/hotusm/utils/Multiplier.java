package com.hotusm.utils;

public class Multiplier {
	
	public int multiply(int x,int y){
		int total=0;
		for(int i=0;i<x;i++){
			total=add(total,y);
		}
		return total;
	}
	
	private int add(int x,int y){return x+y;}
	
	public static void main(String[] args) {
		Multiplier m=new Multiplier();
		int num=m.multiply(3, 15);
		System.out.println("num:"+num);
	}
}
