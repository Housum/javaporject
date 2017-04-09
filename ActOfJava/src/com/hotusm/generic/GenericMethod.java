package com.hotusm.generic;

import java.util.List;


/**
 * 
 * @author Hotusm
 * @since 2016-03-06
 * 1.泛型方法的使用,泛型方法会自动的参数推断
 * 2.泛型方法和泛型类之间是没有很想干的联系的,就算不是泛型
 * 类的话,也可以定义泛型方法。参数推断的含义是说
 * 对于方法的参数,java会自行的进行参数识别，而不用
 * 像类泛型一样需要在类后面显示的指定出来
 * 
 */
public abstract class GenericMethod {
	
	/**
	 * 泛型方法或自动识别传入的类型,
	 * 而不需要客户端程序员知道多的情况下就能实现
	 * 
	 * @param t
	 */
	public <T> void set(T t){
		System.out.println(t);
	}
	public <T,X> void set1(T t,X x){
		System.out.println();
	}
	//参数列表可以很完美的和泛型进行结合
	public abstract <T> List<T> makeList(T...arg);
	
	public static void main(String[] args) {
		//new GenericMethod().set(1234);
	}
}
class G extends GenericMethod{

	@Override
	public <T> List<T> makeList(T... arg) {
		return null;
	}
}