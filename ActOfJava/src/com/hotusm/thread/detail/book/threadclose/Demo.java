package com.hotusm.thread.detail.book.threadclose;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 *	1.�̷߳��:
 *		�����ʿ��Թ���Ŀɱ�����ʱ,ͨ����Ҫʹ��ͬ��,һ�ֱ���ͬ���ķ�ʽ����
 *		����������,�����Ļ� ��ʹ��������������̰߳�ȫ��  ����ͨ���̷߳�յ�
 *		��ʽ  Ҳ�ܹ���Ϊ�̰߳�ȫ�ģ�����Ҳ�������
 *	2.��ֹ�߳�����ж��ַ���
 *    2.1ջ���  ��ʾ����ֻ���ھֲ��������ܷ��ʶ���  ����һ������,��Ȼ����Ҫʹ�õ�ĳһ���ڲ���
 *    	 ���ǲ�����ֱ�ӷ�������  ����ͨ��һ���ֲ��������������� 
 *    2.2�ڶ��ַ�ʽ����ʹ�ø��ӹ淶��ThreadLocal,ͬʱ��ĳһ��Ƶ����������ʱ�����ʱ��
 *   	Ҳ����ʹ���������
 */
public class Demo {
	
	private static ThreadLocal<Connection> connectionHandler
			=new ThreadLocal<Connection>(){

				@Override
				protected Connection initialValue() {
					//ʹ�����ַ�ʽ��ôÿһ���̶߳����Լ�����
					try {
						return DriverManager.getConnection("url");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return null;
				}
		
	};
}
