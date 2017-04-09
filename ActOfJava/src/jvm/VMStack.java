package jvm;

import java.io.IOException;


/**
 * 
 * 平时我们说指的栈就是JAVA虚拟机栈,更确切的说是
 * JAVA虚拟机栈中的局部变量表
 * 
 * @author Hotusm
 *
 */
public class VMStack {

	public void a(){
		b();
	}
	public void b(){
		a();
	}
	/**
	 * <code>
	 * 	Exception in thread "main" java.lang.StackOverflowError
	 * </code>
	 * 1.下面这段代码会直接报错,很明显是死循环了.更深层次的原因就是
	 * 每一个方法执行的时候，就会创建一个栈.当线程请求的栈深度大于虚拟机说允许
	 * 的深度的时候，就会报这个错.
	 * 2.当JAVA虚拟机可以进行动态扩容的时候,无法申请到足够的内存，就会抛出
	 * OutofMemoryError
	 * @param args
	 */
	public static void main(String[] args) {
		new VMStack().a();
	}
}
