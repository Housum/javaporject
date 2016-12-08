package com.hotusm.first;

/***
 * 
 * @author Hotusm
 * 1.测试gc
 * 2.测试静态代码块和构造函数的执行顺序
 */
public class FinalizeTest {
	
	static int i;
	static boolean flag=true;

	static{
		System.out.println("static block");
	}
	
	public FinalizeTest() {
		System.out.println("initialization");
	}
	@Override
	protected void finalize() throws Throwable {
		
		flag=false;
		System.out.println("开始清理堆:"+i++);
		super.finalize();
	}
	/***
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*1.测试gc,当超过作用域之后,考虑进行垃圾清理(调用类的finalize()方法)*/
//		while(flag){
//			new FinalizeTest();
//		}
		
		/*2.测试静态代码块和够着函数的执行顺序*/
		//new FinalizeTest();
	}
	
}
