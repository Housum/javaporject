package com.hotusm.collection;
/**
 * 
 *如果需要实现克隆这个方法的话  那么必须实现Cloneable
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

