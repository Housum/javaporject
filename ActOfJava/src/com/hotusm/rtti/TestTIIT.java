package com.hotusm.rtti;

import org.junit.Test;

/**
 * ��������ʱ������Ϣ
 * @author Hotusm
 *
 */
public class TestTIIT {

	@Test
	public void test(){
		/**
		 * �ڵ�һ��ʹ�����ʱ��,����������Ϣ(���������)
		 * 
		 */
		new A();
		
		try {
			/*������õ�������Class����
			 *:ÿһ���඼��һ��Class����
			 *��ǡ���Ľ��Ǳ���������һ��ͬ����.class�ļ���,Ҳ����˵�����һ�α����ص�ʱ��
			 *�ͻ����.class����һ��Class����
			 * 
			 * */
			
			Class.forName("com.hotusm.tiit.B");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		/**
		 * ��Class���кܶ෴��ķ���ķ��������ȡ�ӿ�,�����ж��Ƿ��ǽӿڵȵ�
		 */
	}
	
	/**
	 * �����泣��
	 */
	@Test
	public void testClass(){
		/*
		 * java���ṩ��һ�ֻ�ȡ��Class������,��:Entity.class��ʽ,
		 * ���ǳ����Ϊ���泣��
		 * 1.�������泣�������ַ�ʽ,���Եõ�������,���ǲ������Class����
		 * */
		Class clazz=DemoEntity.class;
		/*
		 *���ڻ�������,������Ӧ�İ�װ���в�ͬ�ı�ʾ��ʽ
		 * ���������ַ�ʽʱ��ͬ��,ע��:void.class=Void.class
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
