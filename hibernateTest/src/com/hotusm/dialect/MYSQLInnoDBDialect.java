package com.hotusm.dialect;



/**
 * 
 * @author Hotusm
 *
 *	��MYSQL5.5���Ժ�汾��type=InnoDB ��ENGINE=InnoDB  ���档  
	����5.5Ĭ�ϵĴ洢�������InnoDB�����ȥ��������Բ�����Ӱ�졣 
	<code>
		<property name="dialect">com.hotusm.dialect.MYSQLInnoDBDialect</property>
	</code>
 */
public class MYSQLInnoDBDialect extends org.hibernate.dialect.MySQLInnoDBDialect{

	@Override
	public String getTableTypeString() {
		
		return "ENGINE=InnoDB";
	}

	@Override
	public boolean hasSelfReferentialForeignKeyBug() {
		return true;
	}
	
	

}
