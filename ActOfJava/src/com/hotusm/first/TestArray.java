package com.hotusm.first;

/**
 * 
 * @author Hotusm
 * 1.测试数组特性
 */
public class TestArray {
	
	public static void main(String[] args) {
		/*
		 * 1.测试数组的特性,表明数字是和对象一样都是存在
		 * 堆中的,通过数组的引用进行操作
		 */
		char[] array1={'a','b'};
		int hashCode1 = array1.hashCode();
		char[] array;
		array=array1;
		int hashCode = array.hashCode();
		System.out.println("hashCode:"+hashCode+" hashCode1"+hashCode1);
		array[0]='c';
		
		for(char a:array1){
			System.out.println(a);
		}
		/*2.对于无论是基本类型的数组,或者是引用类型的数组,都具有length这个变量*/
		int length=array.length;
		int length1=array1.length;
		System.out.println("length:"+length+" length1:"+length1);
		
		/*3.测试包访问权限,变量没有限制*/
		VisitControl v=new VisitControl();
		int i = v.i;
	}
}
