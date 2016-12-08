package com.hotusm.first;
/**
 * 
 * @author Hotusm
 * 1.����System��һЩ����
 * 2.����javadoc���������ĵ�,�Լ�һЩע��
 */
public class ShowProperties {
	
	/**
	 * ����javadoc �����ĵ�
	 * @param args ���
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("==============list of properties=============");
		System.getProperties().list(System.out);
		System.out.println("==============user.name======================");
		System.out.println(System.getProperty("user.name"));
		System.out.println("==============java.library.path==============");
		System.out.println(System.getProperty("java.library.path"));
	}
	/**
	 * 
	 * @return hello world
	 * @since 2016-02-13
	 * @see java.lang.System
	 */
	public String test(){
		return "hello world";
	}
	
}
