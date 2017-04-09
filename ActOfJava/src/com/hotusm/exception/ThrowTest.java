package com.hotusm.exception;
/*
 * 测试异常:
 * 异常抛出引用
 * */

public class ThrowTest {
	
	public static void main(String[] args) {
		try {
			test();
			//int i=1/0;
			/*
			 *当捕获到异常的时候,和java其他的对象一样
			 *将使用new在堆中创建一个对象 
			 */
		} catch (Exception e) {
			String simpleName = e.getClass().getSimpleName();
			System.out.println(simpleName);
			//输出异常调用栈轨迹
			//可以直接通过getStackTrace获得调用栈信息
			e.printStackTrace(System.out);
			//显示每一个栈帧的信息
			StackTraceElement[] stackTrace = e.getStackTrace();
			int l = stackTrace.length;
			System.out.println("开始打印栈信息...");
			for(StackTraceElement st:stackTrace){
				System.out.println(st.toString());
			}
			System.out.println(l);
		}
		
		
	}
	public static void test(){
		test1();
	}
	public static void test1(){
		int i=10/0;
	}
}
