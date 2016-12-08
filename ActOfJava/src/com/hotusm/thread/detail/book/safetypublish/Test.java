package com.hotusm.thread.detail.book.safetypublish;

public class Test {

	public static void main(String[] args) {
		
		NotSafePublish p=new NotSafePublish();
		Bo bo=new Bo();
		bo.setMsg("124");
		p.setSecond(bo);
		//从这个例子中我们可以看出来 一个对象的正确发布方式  
		//如果我们直接返回的是类中的引用或者或者设置值的时候直接将引用赋值的话 
		//那么这个发布的对象就已经溢出了
		System.out.println(p.getSecond().getMsg());
		bo.setMsg("567");
		System.out.println(p.getSecond().getMsg());
	}
}
