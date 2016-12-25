package com.hotusm.web.func;

import java.util.UUID;

/**
 * 
 * ע��h2�Զ��庯��
 * @author Hotusm
 *
 *<code>
 *	CREATE ALIAS [IF NOT EXISTS] newFunctionAliasName [DETERMINISTIC] FOR classAndMethodName
 *	�����ڵ��ǿ�ѡ��
 *
 *</code>
 */
public class H2Func {

	/**
	 * ����UUID
	 * 1.ע��ע��ķ��������Ǿ�̬��,������public��
	 * ע��:CREATE ALIAS UUID FOR "com.hotusm.web.func.H2Func.uuid"
	 * ע���������֮����ܹ���Ч
	 * 2.ִ����������֮��,�Ϳ���ִ��
	 * select UUID();
	 */
	public static String uuid(){
		
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
}
