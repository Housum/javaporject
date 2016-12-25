package com.hotusm.dialect;



/**
 * 
 * @author Hotusm
 *
 *	在MYSQL5.5及以后版本中type=InnoDB 由ENGINE=InnoDB  代替。  
	由于5.5默认的存储引擎就是InnoDB，因此去掉这个属性不会有影响。 
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
