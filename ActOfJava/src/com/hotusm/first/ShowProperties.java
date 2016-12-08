package com.hotusm.first;
/**
 * 
 * @author Hotusm
 * 1.测试System中一些属性
 * 2.测试javadoc命令生成文档,以及一些注释
 */
public class ShowProperties {
	
	/**
	 * 测试javadoc 生成文档
	 * @param args 入参
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
