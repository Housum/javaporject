package com.hotusm.test;

import java.util.LinkedHashSet;
import java.util.Set;

public class Prime {
	
	protected String num;
	
	private String num1;
	
	String num2;
	
	public String num3;

	public void print(Integer num){
		
		Set<Integer> set=new LinkedHashSet<Integer>();
		for(int i=1;i<=num;i++){
			for(int j=1;j<i;j++){
				boolean f=true;
				for(int k=1;k<j;k++){
					if(j%k==0&&k!=1&&k!=j){
						f=false;
					}
				}
				if(f){
					set.add(j);
				}
			}
		}
		if(set.contains(1)){
			set.remove(1);
		}
		for(Integer prime:set){
			System.out.println(prime);
		}
	}
	public static void main(String[] args) {
		new Prime().print(100);
	}
}
