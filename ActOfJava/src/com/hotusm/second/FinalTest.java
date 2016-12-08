package com.hotusm.second;

/**
 * 
 * @author Hotusm
 * 1.final的使用:数据,方法和类
 */
public class FinalTest {
	/*
	 * 1.对于final的引用来说,这个引用只能指向一个对象而不能指向其他的
	 * 对象,但是并不能保证对象不变,下面的就是例子
	 * 
	 */
	private static T t=new T();
	private static final T tt=t;
	/*
	 * 5.对于基本变量,因为它们都是存在于堆栈中,所以数值
	 * 是不变的
	 * */
	private final int i=10;
	
	/*
	 * 6.类中所以的private方法都隐式的指定为final的,
	 * 所以子类是不能够覆写并且继承它的.
	 * */
	private void say(){
		System.out.println("private method");
	}
	public static void main(String[] args) {
		System.out.println("tt:"+tt.i+" t:"+t.i);
		t.i=10;
		System.out.println("tt:"+tt.i+" t:"+t.i);
	}
}

class T{
	int i=0;
}
/*2.空白final,在定义的时候没有进行赋值,但是在构造函数中必须进行初始化,
 * 否会报错
 * */
class FinalTest1{
	
	private final String name;
	
	public FinalTest1() {
		//在构造器中进行初始化
		name="default";
	}
	
	public String getName() {
		return name;
	}
	/*3.final方法
	 * 现在方法前面加上final的作用是防止子类覆写,并且在子类
	 * 中是不可见的
	 * */
	public final void say(){
		System.out.println("final method");
	}
	
}
/*
 * 4.FinalTest1中的say()方法对于B这个导出类来说
 * 是不可见的
 * */
class D extends FinalTest1{
	
}
/*
 * 7.
 * ①使用final修饰的类不能被继承
 * ②final修饰的类的方法默认都是final的
 * 
 * */
final class C{
	int i=10;
	float j=10f;
	//方法默认是final的
	 void say(){
		// alternative_hashing_threshold_default
	 }
}
//不能基层由final修饰的类
//class DB extends C{
//	
//}