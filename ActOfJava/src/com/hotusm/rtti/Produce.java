package com.hotusm.rtti;

import net.sf.cglib.proxy.Factory;

public class Produce implements FacotoryBean<Object>{
	
	public static void main(String[] args) {
		FacotoryBean p=new Produce();
		//����true
		boolean f = Factory.class.isAssignableFrom(Factory.class);
		//����false
		boolean f1 = Factory.class.isAssignableFrom(p.getClass());
		System.out.println(f+" "+f1);
		
		//��֤�Ƿ�Ϊ�����ʾ��
		boolean isInstance = FacotoryBean.class.isInstance(p);
		System.out.println(isInstance);
	}
}
