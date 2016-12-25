package com.hotusm.junit;

import junit.framework.TestCase;
/**
 *
 * 继承TestCase 就像@Test
 */
public class MyTestCase extends TestCase{
	
	/**
	 * 测试方法都必须以test开头
	 */
	public void testSimple(){
		System.out.println("111");
		//使用断言  
		assertNotNull("111");
	}
}
