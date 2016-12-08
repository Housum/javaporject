package com.hotusm.GenericTest;

/**
 * 
 * 泛型实现了参数化类型的作用(将一个类型作为参数进行操作)
 * 
 * 1.使用泛型持有对象,
 * 只需要在类后面加上<X>
 * @author Hotusm
 * @since 2016-03-06
 * 
 * @param <T>我们成T为类型参数
 */
public class ClassHodler<T>{
	
	//我们使用的类型
	private T vo;

	public T getVo() {
		return vo;
	}
	public void setVo(T vo) {
		this.vo = vo;
	}
	@Override
	public String toString() {
		System.out.println("vo: "+vo);
		return super.toString();
	}
	
	public static void main(String[] args) {
		ClassHodler<String> gh=new ClassHodler<String>();
		gh.setVo("hotusm");
		gh.toString();
		
	}
}
