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
	
	/** intern()�����������˼����˵���ַ����������в������
		�ַ����Ƿ���ڣ���������ڵĻ�,��ô�ڲ������д�����������ַ��������ã����
		���ڣ���ô��ֱ�ӷ�������ַ���������
	 **/
	@Test
	public void testIntern(){
		String a="abcd";

		String b=a.intern();
		String c=new String("abcd");
		/*
		 * ����������ص���true,��Ϊ���ʱ���ַ������������Ѿ����ڡ�abcd������ַ���������
		 * 
		 * */
		System.out.println(b=="abcd");
		//false
		System.out.println(c=="abcd");
		/*
		 * false  ����new�����Ķ���(Ҳ����˵ֱ���ڶ��з����),������ȥ����������(�������ʱ�����ǿ���ʹ�����new������intern()������ȡ).
		 * ���ǵ�c���ڶ��еģ��ͳ������еĺ��޹�ϵ
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
		//�����Ƚϵľ��ǵ�ַ�ˣ����ܱȽϺ�
		
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
