package com.hotusm.web.func;

import java.util.UUID;

/**
 * 
 * 注册h2自定义函数
 * @author Hotusm
 *
 *<code>
 *	CREATE ALIAS [IF NOT EXISTS] newFunctionAliasName [DETERMINISTIC] FOR classAndMethodName
 *	方框内的是可选的
 *
 *</code>
 */
public class H2Func {

	/**
	 * 生成UUID
	 * 1.注意注册的方法必须是静态的,必须是public的
	 * 注册:CREATE ALIAS UUID FOR "com.hotusm.web.func.H2Func.uuid"
	 * 注意必须重启之后才能够生效
	 * 2.执行上面的语句之后,就可以执行
	 * select UUID();
	 */
	public static String uuid(){
		
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}
