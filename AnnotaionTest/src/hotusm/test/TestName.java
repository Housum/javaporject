package hotusm.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;

import com.hotusm.annctation.Constraints;
import com.hotusm.annctation.DBTable;
import com.hotusm.annctation.Name;
import com.hotusm.annctation.SQLFloat;
import com.hotusm.annctation.SQLInteger;
import com.hotusm.annctation.SQLString;
import com.hotusm.perstence.DBOpreration;

public class TestName {
	
	@Name(name="1234")
	public static void say(){
		System.out.println("run say()");
	}
	
	public static void main(String[] args) {
		
		StringBuffer sb=new StringBuffer();
		try {
			Class<?> clazz=Class.forName("com.hotusm.entity.User");
			//获取类上面的注解
			DBTable db=clazz.getAnnotation(DBTable.class);
			if(db!=null){
				String dbName="";
				if("".equals(db.name())){
					dbName=clazz.getSimpleName();
				}else{
					dbName=db.name();
				}
				sb.append("CREATE TABLE "+dbName.toUpperCase()+" (");
			}else{
				return;
			}
			
			//增加字段上面注解
			Field[] declaredFields = clazz.getDeclaredFields();
			if(declaredFields!=null){
				for(Field f:declaredFields){
					//判断字段里面有没有注解
					Annotation[] annotations = f.getAnnotations();
					if(annotations.length<1)break;
					
					if(annotations[0] instanceof SQLInteger){
						String columnName="";
						SQLInteger sInt=(SQLInteger) annotations[0];
						//判断名称是否有name 否则使用默认的
						if(sInt.name().length()<1){
							columnName=f.getName();
						}else{
							columnName=sInt.name();
						}
						Constraints constraints = sInt.constraints();
						String str=getConstrains(constraints);
						sb.append(columnName.toUpperCase()+" INT"+str+",");
					}
					
					if(annotations[0] instanceof SQLString){
						String columnName="";
						Integer num=0;
						SQLString sString=(SQLString) annotations[0];
						if(sString.name().length()<1){
							columnName=f.getName();
						}else{
							columnName=sString.name();
						}
						num=sString.value();
						Constraints constraints = sString.constraints();
						String str=getConstrains(constraints);
						sb.append(columnName.toUpperCase()+" VARCHAR("+num+")"+str+",");
					}
					
					if(annotations[0] instanceof SQLFloat){
						String columnName="";
						SQLFloat sFloat=(SQLFloat) annotations[0];
						if(sFloat.name().length()<1){
							columnName=f.getName();
						}else{
							columnName=sFloat.name();
						}
						sb.append(columnName.toUpperCase()+" FLOAT,");
					}
					
				}
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		String sqlString=sb.substring(0, sb.length()-1)+")";
		try {
			DBOpreration.getStatement(sqlString, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static String getConstrains(Constraints con){
		String constraints="";
		if(con.allowNull()==true){
			constraints+=" NOT NULL";
		}
		if(con.primaryKey()==true){
			constraints+=" PRIMARY KEY";
		}
		if(con.unique()==true){
			constraints+=" UNIQUE";
		}
		return constraints;
	}
}
