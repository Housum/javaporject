package com.hotusm.collection;
/**
 * 
 *�����Ҫʵ�ֿ�¡��������Ļ�  ��ô����ʵ��Cloneable
 */
public class CloneTest implements Cloneable{

	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		
		CloneTest clone = (CloneTest) new CloneTest().clone();
		System.out.println(clone);
	}
}

