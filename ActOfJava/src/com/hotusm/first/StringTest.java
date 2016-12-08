package com.hotusm.first;

import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 
 * @author Hotusm
 *
 */
public class StringTest {
	
	public static void main(String[] args) {
////		char[] c=new char[1];
////		int x=9;
////		double f=4.53434;
////		c[0]='1';
////		//c[1]='2';
////		String str=new String(c);
////		System.out.println(str.length());
////		System.out.format("%d %s", x,f);
//		
//		String ids="1";
//		
//		String[] idArray = ids.split(",");
//		for(String id:idArray){
//			System.out.println(id);
//		}
		
		
		int hashCode = "M150755b4rOo".hashCode();
		System.out.println(hashCode);
	}
	private static final String b="bbb";
	
	/** intern()这个方法的意思就是说在字符串常量池中查找这个
		字符串是否存在，如果不存在的话,那么在产量池中创建返回这个字符串的引用，如果
		存在，那么就直接返回这个字符串的引用
	 **/
	@Test
	public void testIntern(){
		String a="abcd";

		String b=a.intern();
		String c=new String("abcd");
		/*
		 * 下面这个返回的是true,因为这个时候，字符串常量池中已经存在‘abcd’这个字符串常量了
		 * 
		 * */
		System.out.println(b=="abcd");
		//false
		System.out.println(c=="abcd");
		/*
		 * false  对于new出来的对象(也就是说直接在堆中分配的),并不会去常量池中找(但是这个时候我们可以使用这个new出来的intern()方法获取).
		 * 这是的c是在堆中的，和常量池中的毫无关系
		 * 
		 */
		System.out.println(b==c);
		
		
		String s0="kvill";
		String s1=new String("kvill");
		String s2=new String("kvill");
		//false
		System.out.println(s0==s1);
		s1.intern();
		s2=s2.intern();
		//false
		System.out.println(s0==s1);
		//true
		System.out.println(s2==s0);
	}
	
	@Test
	public void test2(){
		//这样比较的就是地址了，性能比较好
		
		String a="a".intern();
		switch(a){
			case "a":System.out.println("aaa");break;
			case "b":System.out.println("bbb");break;
		}
	}
	
}
class A{
	private final char[] value;
	
	public A(){
		value=new char[0];
	}
	
	public A(A a) {
		this.value=a.value;
	}
	
}
