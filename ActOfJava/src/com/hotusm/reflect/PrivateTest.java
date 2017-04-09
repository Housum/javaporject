package com.hotusm.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class PrivateTest {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception{
		Class clazz=Base.class;
		System.out.println("---------------------------------");
		//ͨ�����Class�����ȡ���ඨ������еķ���
		Method[] mds = clazz.getDeclaredMethods();
		
		for(Method m:mds){
			System.out.println("method: "+m.toGenericString());
			System.out.println("method: "+m.toString());
		}
		System.out.println("---------------------------------");
		Base a=(Base) clazz.newInstance();
		try {
			Method m=clazz.getDeclaredMethod("f");
			//������Щ���Ǿ���publicȨ�޵ķ�����˵,��ʹ��ǰ��Ҫ��������setAccessible
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
			//�������η�����public���ֶκͷ�������Ҫ��������
			df.setAccessible(true);
			df.set(a, "hotusm");
			//�õ�getName����
			Method m1=clazz.getDeclaredMethod("getName");
			System.out.println(m1.invoke(a));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//��������Щfinal�ֶ���˵,�������׳��쳣,���ǲ��ܶ�final�ֶν����޸�
		Field df1 = clazz.getDeclaredField("id");
		df1.setInt(a, 1);
		//��Ȩ��
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
