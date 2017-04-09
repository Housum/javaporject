package com.hotusm.designpattern;

/**
 * 
 * 
 * @author Hotusm
 * @since 2016-03-11
 * 设计模式->迭代子模式
 * 1.黑箱聚集与内禀迭代子
 *  ①如果一个聚集的接口没有提供修改聚集元素的方法，这样的接口就是所谓的窄接口。
　　聚集对象为迭代子对象提供一个宽接口，而为其他对象提供一个窄接口。换言之，
        聚集对象的内部结构应当对迭代子对象适当公开，以便迭代子对象能够对聚集对象有足够的了,
        从而可以进行迭代操作。但是，聚集对象应当避免向其他的对象提供这些方法，因为其他对象应
        当经过迭代子对象进行这些工作，而不是直接操控聚集对象。
 *       
 * ②在JAVA语言中，实现双重接口的办法就是将迭代子类设计成聚集类的内部成员类。这样迭代子
 * 对象将可以像聚集对象的内部成员一样访问聚集对象的内部结构。下面给出一个示意性的实现，说
 * 明这种双重接口的结构时怎么样产生的，以及使用了双重接口结构之后迭代子模式的实现方案。这种
 * 同时保证聚集对象的封装和迭代子功能的实现的方案叫做黑箱实现方案。
 * 
 * ③由于迭代子是聚集的内部类，迭代子可以自由访问聚集的元素，所以迭代子可以自行实现迭代功能
 * 并控制对聚集元素的迭代逻辑。由于迭代子是在聚集的结构之内定义的，因此这样的迭代子又叫做内
 * 禀迭代子（Intrinsic Iterator）。
 * 
 * 
 */
public class IteratorTest1 {
	
	public static void main(String[] args) {
		ConcreteAggregate<String> ca=new ConcreteAggregate<String>(new String[]{"111","222","333","444"});
		Iterator2<String> iterator2 = ca.iterator2();
		while(!iterator2.isDone()){
			System.out.println(iterator2.currentItem());
			iterator2.next();
		}
	}
}
/**
 * 
 * 抽象迭代子
 * @author Hotusm
 *
 * @param <U>
 */
interface Iterator2<U>{
	
	public void first();
	public void next();
	public boolean isDone();
	public U currentItem();
	
}

/**
 * 
 * 抽象聚集
 * @author Hotusm
 *
 * @param <U>
 */
abstract class Aggregate1<U>{
	
	public abstract Iterator2<U> iterator2();
}

class ConcreteAggregate<U> extends Aggregate1<U>{
	
	private U[] us;
	

	public ConcreteAggregate(U[] us) {
		super();
		this.us = us;
	}

	@Override
	public Iterator2<U> iterator2() {
		
		return new ConcreteInterator2<U>();
	}
	
	
	private class ConcreteInterator2<K> implements Iterator2<U>{
		private int index=0;
		private int size=us.length;
		
		@Override
		public void first() {
			index=0;
		}

		@Override
		public void next() {
			if(index<size){
				index++;
			}
		}

		@Override
		public boolean isDone() {
			return index>=size;
		}

		@Override
		public U currentItem() {
			if(index<size){
				return us[index];

			}
			return null;
		}
		
		
	}
}



