package com.hotusm.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class PrivateTest {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception{
		Class clazz=Base.class;
		System.out.println("---------------------------------");
		//通过类的Class对象获取到类定义的所有的方法
		Method[] mds = clazz.getDeclaredMethods();
		
		for(Method m:mds){
			System.out.println("method: "+m.toGenericString());
			System.out.println("method: "+m.toString());
		}
		System.out.println("---------------------------------");
		Base a=(Base) clazz.newInstance();
		try {
			Method m=clazz.getDeclaredMethod("f");
			//对于那些不是具有public权限的方法来说,在使用前需要进行设置setAccessible
			m.setAccessible(true);
			m.invoke(a);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------");
		
		Field[] dfs = clazz.getDeclaredFields();
		for(Field f:dfs){
			System.out.println("field:"+f.getName());
		}
		try {
			Field df = clazz.getDeclaredField("name");
			//对于修饰符不是public的字段和方法都需要进行设置
			df.setAccessible(true);
			df.set(a, "hotusm");
			//得到getName方法
			Method m1=clazz.getDeclaredMethod("getName");
			System.out.println(m1.invoke(a));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//而对于那些final字段来说,并不会抛出异常,但是不能对final字段进行修改
		Field df1 = clazz.getDeclaredField("id");
		df1.setInt(a, 1);
		//包权限
		Field df2 = clazz.getDeclaredField("remark");
		df2.setAccessible(true);
		df2.setInt(a, 100);
		
	}
}
class Base{
	
	private String name;
	private Integer age;
	
	protected double price;
	public int color;
	
	private final int id=0;
	int remark;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}

	private void f(){
		System.out.println("f...");
	}
	
}
