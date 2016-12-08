package com.hotusm.designModel;

import java.util.Arrays;

/**
 * 
 * @author Hotusm
 * 1.���ģʽ->����ģʽ
 */
public class StrategyTest {
	
	public static String s="you aRe very good ";
	/*
	 * 2.
	 * �ٿ��Խ��ܲ�ͬ���͵�Processor,���в�ͬ�Ĵ���ʽ
	 * �����������,����һ���ܹ����������ݵĲ�������Ĳ�ͬ�����в�ͬ��Ϊ�ķ���
	 * ��Ϊ�������ģʽ,�����������Processor���ǲ���.
	 * �����෽��������Ҫִ�е��㷨�й̶�����Ĳ���,�����԰����仯�Ĳ���
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
/*���Ի���*/
class Processor{
	
	public String name(){
		return getClass().getSimpleName();
	}
	Object process(Object input){
		return input;
	}
}
/*
 * ����1
 * */
class Upcase extends Processor{

	@Override
	Object process(Object input) {
		return ((String)input).toUpperCase();
	}
}
/*
 * ����2
 * */
class Downcase extends Processor{

	@Override
	Object process(Object input) {
		return ((String)input).toLowerCase();
	}
	
}
/*
 * ����3
 * */
class Splitter extends Processor{

	@Override
	Object process(Object input) {
		return Arrays.toString(((String)input).split(" "));
	}
	
}



