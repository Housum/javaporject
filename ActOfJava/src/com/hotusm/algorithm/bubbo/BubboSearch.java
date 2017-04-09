package com.hotusm.algorithm.bubbo;

public class BubboSearch {

	public static void main(String[] args) {
		
		int num[]=new int[]{1,2,4,3};
		
		for(int i=0;i<num.length;i++){
			
			for(int j=i;j<num.length;j++){
				
				if(num[i]<num[j]){
					num[i]=num[i]^num[j];
					num[j]=num[i]^num[j];
					num[i]=num[i]^num[j];
				}
			}
		}
		

		for(int n:num){
			System.out.println(n);
		}
	}
}
