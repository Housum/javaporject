package com.hotusm.rtti;

import net.sf.cglib.proxy.Factory;

public class Produce implements FacotoryBean<Object>{
	
	public static void main(String[] args) {
		FacotoryBean p=new Produce();
		//返回true
		boolean f = Factory.class.isAssignableFrom(Factory.class);
		//返回false
		boolean f1 = Factory.class.isAssignableFrom(p.getClass());
		System.out.println(f+" "+f1);
		
		//验证是否为这个的示例
		boolean isInstance = FacotoryBean.class.isInstance(p);
		System.out.println(isInstance);
	}
}
