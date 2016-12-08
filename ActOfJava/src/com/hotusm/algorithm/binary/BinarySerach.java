package com.hotusm.algorithm.binary;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月29日   <br/>
 * @description 二分法查找
 */
public class BinarySerach {
	
	
	public static<T extends Comparable<T>> int binarySearch(T[] x,int offset,int length,T key){
		
		if(offset<=length){
			int mid=offset+((length-offset)>>1);
			
			if(key.compareTo(x[mid])==0){
				
				return mid;
			}
			
			if(key.compareTo(x[mid])<0){
				return binarySearch(x, offset, mid-1, key);
			}else{
				return binarySearch(x, mid+1, length, key);
			}
		}
		return -1;
	}
 }
