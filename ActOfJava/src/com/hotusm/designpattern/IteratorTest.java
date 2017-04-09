package com.hotusm.designpattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Hotusm
 * @since  2016-03-11
 * 
 *	设计模式->迭代子设计模式(游标模式)
 *  迭代子模式又叫游标(Cursor)模式，是对象的行为模式。
 *  迭代子模式可以顺序地访问一个聚集中的元素而不必暴露聚集的内部表象
 *  (internal representation)。
 *  
 *  1.白箱聚集与外禀迭代子:
 *  ①如果一个聚集的接口提供了可以用来修改聚集元素的方法，这个接口就是所谓的宽接口。
 *  如果聚集对象为所有对象提供同一个接口，也就是宽接口的话，当然会满足迭代子模式对
 *  迭代子对象的要求。但是，这样会破坏对聚集对象的封装。这种提供宽接口的聚集叫做白箱聚集。
 *  聚集对象向外界提供同样的宽接口，如下图所示：
 *  ②　由于聚集自己实现迭代逻辑，并向外部提供适当的接口，使得迭代子可以从外部控制聚集元素
 *  的迭代过程。这样一来迭代子所控制的仅仅是一个游标而已，这种迭代子叫做游标迭代子（Cursor
 *   Iterator）。由于迭代子是在聚集结构之外的，因此这样的迭代子又叫做外禀迭代子（Extrinsic Iterator）
 *  ③　现在看一看白箱聚集与外禀迭代子的实现。一个白箱聚集向外界提供访问自己内部元素的接口（称作遍历方法或者Traversing Method）,
 *  从而使外禀迭代子可以通过聚集的遍历方法实现迭代功能。
 *  
 *  
 */
public class IteratorTest {

	public static void main(String[] args) {
		Aggregate<String> aggregate=new ConcreteAggreage<String>(new String[]{"111","222","333","444"});
		Iterator1<String> inerator1 = aggregate.inerator1();
		while(!inerator1.isDone()){
			System.out.println(inerator1.currrentItem());
			inerator1.next();
		}
	}
	
	/**
	 * 
	 * 迭代子设计模式的各种概念
	 */
	public void testIterator(){
		/**
		 * List:聚集(Aggregate)角色,此抽象角色给出创建迭代子(Iterator)对象的接口
		 * ArrayList:具体聚集(ConcreteAggregate)角色：实现了创建迭代子(Iterator)对象的接口，返回一个合适的具体迭代子实例
		 * 聚集中是具体的聚集元素 
		 * 
		 */
		
		List<String> list=new ArrayList<String>();
		/**
		 * 抽象迭代子(Iterator)角色：此抽象角色定义出遍历元素所需的接口。
		 * 
		 */
		Iterator<String> iterator = list.iterator();
	}
}
/**
 * 
 * 抽象迭代子
 * @author Hotusm
 *
 * @param <U>
 */
interface Iterator1<U>{
	
	public void first();
	public void next();
	public boolean isDone();
	public U currrentItem();
}
/**
 * 
 * 具体的迭代子
 * @author Hotusm
 *
 * @param <U>
 */
class ConcreteIterator1<U> implements Iterator1<U>{
	
	private ConcreteAggreage<U> aggregate=null;
	private int size;
	private int index;
	
	public ConcreteIterator1(ConcreteAggreage<U> aggregate) {
		super();
		this.aggregate = aggregate;
		this.size=aggregate.size();
	}

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
	public U currrentItem() {
		return aggregate.getElement(index);
	}
}
/**
 * 
 * 抽象聚集类
 * @author Hotusm
 *
 * @param <U>
 */
abstract class Aggregate<U>{
	
	public abstract Iterator1<U> inerator1();
}
/**
 * 
 *具体的聚集类
 * @author Hotusm
 *
 * @param <U>
 */
class ConcreteAggreage<U> extends Aggregate<U>{
	
	private U[] us=null;
	private int size=0;
	

	public ConcreteAggreage(U[] us) {
		super();
		this.us = us;
		size=us.length;
	}

	@Override
	public Iterator1<U> inerator1() {
		return new ConcreteIterator1<U>(this);
	}
	
	public U getElement(int index){
		if(index<0||index>=size){
			return null;
		}
		
		return us[index];
	}
	
	public int size(){
		return size;
	}
}












