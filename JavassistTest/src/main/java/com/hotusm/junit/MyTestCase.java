package com.hotusm.junit;

import junit.framework.TestCase;
/**
 *
 * �̳�TestCase ����@Test
 */
public class MyTestCase extends TestCase{
	
	/**
	 * ���Է�����������test��ͷ
	 */
	public void testSimple(){
		System.out.println("111");
		//ʹ�ö���  
		assertNotNull("111");
	}
}
