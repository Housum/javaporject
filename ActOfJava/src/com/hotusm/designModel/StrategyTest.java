package com.hotusm.designModel;

import java.util.Arrays;

/**
 * 
 * @author Hotusm
 * 1.设计模式->策略模式
 */
public class StrategyTest {
	
	public static String s="you aRe very good ";
	/*
	 * 2.
	 * ①可以接受不同类型的Processor,具有不同的处理方式
	 * ②像这个例子,创建一个能够根据所传递的参数对象的不同而具有不同行为的方法
	 * 成为策略设计模式,在这个例子中Processor就是策略.
	 * ③这类方法包含所要执行的算法中固定不变的部分,而策略包含变化的部分
	 * 
	 * */
	public static void process(Processor p,Object o){
		System.out.println("use Processor:"+p.name());
		System.out.println(p.process(o));
	}
	
	public static void main(String[] args) {
		Processor p=new Upcase();
		process(p, s);
		p=new Downcase();
		process(p, s);
		p=new Splitter();
		process(p, s);
	}
	
}
/*策略基类*/
class Processor{
	
	public String name(){
		return getClass().getSimpleName();
	}
	Object process(Object input){
		return input;
	}
}
/*
 * 策略1
 * */
class Upcase extends Processor{

	@Override
	Object process(Object input) {
		return ((String)input).toUpperCase();
	}
}
/*
 * 策略2
 * */
class Downcase extends Processor{

	@Override
	Object process(Object input) {
		return ((String)input).toLowerCase();
	}
	
}
/*
 * 策略3
 * */
class Splitter extends Processor{

	@Override
	Object process(Object input) {
		return Arrays.toString(((String)input).split(" "));
	}
	
}



