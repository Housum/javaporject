package com.hotusm.algorithm.datastructure;


public class KMP {

	public static void main(String[] args) {
		String str="aaa";
		System.out.println(str.substring(str.length(),str.length()));
		pritln(getArr(str));
	}
	
	public static int[] getArr(String str){
		int[] nexts=new int[str.length()];
		nexts[0]=0;
		nexts[1]=1;
		if(str.length()<3){
			return nexts;
		}
		for(int j=2;j<str.length();j++){
			int index=1;
			for(int i=0;i<j-1;i++){
				if(str.substring(0,i+1).equals(str.substring(j-i-1,j))){
					index++;
				}
			}
			nexts[j]=index;
		}
		return nexts;
	}
	
	public static void pritln(int[] arr){
		
		for(int a:arr){
			System.out.print("["+a+"]");
		}
	}
}
