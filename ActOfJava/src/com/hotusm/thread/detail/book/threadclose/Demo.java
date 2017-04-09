package com.hotusm.thread.detail.book.threadclose;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 *	1.线程封闭:
 *		当访问可以共享的可变数据时,通常需要使用同步,一种避免同步的方式就是
 *		不共享数据,这样的话 即使这个对象本身并不是线程安全的  但是通过线程封闭的
 *		方式  也能够变为线程安全的，数据也不会溢出
 *	2.防止线程溢出有多种方案
 *    2.1栈溢出  表示的是只能在局部变量才能访问对象  比如一个方法,虽然其中要使用到某一个内部类
 *    	 但是并不是直接返回引用  而是通过一个局部变量来进行引用 
 *    2.2第二种方式就是使用更加规范的ThreadLocal,同时当某一个频繁操作的临时对象的时候
 *   	也可以使用这个技术
 */
public class Demo {
	
	private static ThreadLocal<Connection> connectionHandler
			=new ThreadLocal<Connection>(){

				@Override
				protected Connection initialValue() {
					//使用这种方式那么每一个线程都有自己连接
					try {
						return DriverManager.getConnection("url");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return null;
				}
		
	};
}
