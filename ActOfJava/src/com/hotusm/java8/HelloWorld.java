package com.hotusm.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloWorld {

	public static void main(String[] args) {
		
		//lambda ���ʽ
		List<String> names=new ArrayList<String>();
		
		names.add("hotusm");
		names.add("hhhh");
		names.add("chenweixia");
		Collections.sort(names,(String a,String b)->{
			return b.compareTo(a);
		});
		
		//
		Collections.sort(names,(String a,String b)->b.compareTo(a));
		
		//���Ӽ򵥵ķ�ʽ  1. JVM �ܹ����������Ƶ�  2.���ں�����ֻ��һ�д���� ����ȥ��������{}�Լ�
		//return �ؼ���
		Collections.sort(names,(a,b)->b.compareTo(a));
		
		
	}
}

//JAVA8 ������  �ܹ��ڽӿ����������˳��󷽷��ķ���
//������Ҫ��default������static���͵ķ���
//��Ϊ��JAVA��û�ж�̳еĸ���   ������ǰͨ������ӿ�
//������Ҫ��ʵ�ֳ��󷽷�  �������ڿ����ڽӿ�����Ʒ���
interface Formula{
	
	double calculate(int a);
	
	static double sqrt(int a){
		return Math.sqrt(a);
	}
}



