package com.hotusm.second;
/**
 * 
 * @author Hotusm
 * 1.抽象测试
 */
public class AbstractTest {
	
	public static void main(String[] args) {
		
	}
	
}

abstract class AA{
	/*  1.作为一个基类,如果强制导出类必须覆写
	 * 方法,那么将方法设置为abstract,同时如果
	 * 某一个类具有abstract的方式时,那么这个类也必须声明为abstract
	 * 
	 * */
	abstract void say();
	void say1(){
		System.out.println("say1");
	}
}
/*2.
 * ①对于继承抽象类的子类,必须对抽象方法进行覆写
 * ②如果不对抽象方法进行覆写,那么子类也必须声明成抽象(abstract)
 * 
 * */
class BB extends AA{

	@Override
	void say() {
		System.out.println("say");
	}}
