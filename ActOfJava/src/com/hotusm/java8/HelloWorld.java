package com.hotusm.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloWorld {

	public static void main(String[] args) {
		
		//lambda 表达式
		List<String> names=new ArrayList<String>();
		
		names.add("hotusm");
		names.add("hhhh");
		names.add("chenweixia");
		Collections.sort(names,(String a,String b)->{
			return b.compareTo(a);
		});
		
		//
		Collections.sort(names,(String a,String b)->b.compareTo(a));
		
		//更加简单的方式  1. JVM 能够进行类型推导  2.对于函数体只有一行代码的 可以去掉大括号{}以及
		//return 关键字
		Collections.sort(names,(a,b)->b.compareTo(a));
		
		
	}
}

//JAVA8 新特新  能够在接口中声明除了抽象方法的方法
//但是需要是default或者是static类型的方法
//因为在JAVA中没有多继承的概念   所以以前通过多个接口
//但是需要都实现抽象方法  但是现在可以在接口中设计方法
interface Formula{
	
	double calculate(int a);
	
	static double sqrt(int a){
		return Math.sqrt(a);
	}
}



