package com.hotusm.rtti;

import org.junit.Test;

/**
 * 测试运行时类型信息
 * @author Hotusm
 *
 */
public class TestTIIT {

	@Test
	public void test(){
		/**
		 * 在第一次使用类的时候,会加载类的信息(包括类变量)
		 * 
		 */
		new A();
		
		try {
			/*加载类得到这个类的Class对象
			 *:每一个类都有一个Class对象
			 *更恰当的将是被保存在在一个同名的.class文件中,也就是说在类第一次被加载的时候
			 *就会根据.class创建一个Class对象
			 * 
			 * */
			
			Class.forName("com.hotusm.tiit.B");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		/**
		 * 在Class中有很多反射的反射的方法比如获取接口,父类判断是否是接口等等
		 */
	}
	
	/**
	 * 类字面常量
	 */
	@Test
	public void testClass(){
		/*
		 * java还提供了一种获取类Class的引用,即:Entity.class方式,
		 * 我们称这个为字面常量
		 * 1.对于字面常量的这种方式,所以得到了引用,但是不会加载Class对象
		 * */
		Class clazz=DemoEntity.class;
		/*
		 *对于基本类型,他们相应的包装类有不同的表示方式
		 * 下面这两种方式时相同的,注意:void.class=Void.class
		 */
		Class clazz1=boolean.class;
		clazz1=Boolean.TYPE;
		
	}
}
class A{
	static{
		System.out.println("loading A");
	}
}
class B{
	static{
		System.out.println("loading B");
	}
}
