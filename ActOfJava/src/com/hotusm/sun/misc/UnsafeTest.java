package com.hotusm.sun.misc;

import java.lang.reflect.Field;

public class UnsafeTest {
	
	
	public static void main(String[] args) {
//		try {
//			PrivateClass PrivateClass =(PrivateClass)getUnsafe().allocateInstance(PrivateClass.class);
//			PrivateClass.setName("2222");
//			System.out.println(PrivateClass);
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		}
		int num=3;
		int num1=4;
		new UnsafeTest().compareAndSwap(num, num1);
		System.out.println(num+" "+num1);
	}
	
	public boolean compareAndSwap(int expect,int update){
		
		return getUnsafe().compareAndSwapInt(this, 1, expect, update);
	}
	
	
	public static sun.misc.Unsafe getUnsafe(){
		try {
			Field f = sun.misc.Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference  
			f.setAccessible(true);  
			sun.misc.Unsafe unsafe = (sun.misc.Unsafe) f.get(null);
			return unsafe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
