package com.hotusm.designpattern;

/**
 * 
 * @author Hotusm
 * 1.设计模式->工厂方法设计模式
 * 
 * 使用多继承实现工厂方法设计模式,这个模式是自然界的抽象
 * 对于不同的子类具有不同的工厂来进行生产,同时利用多态的性质,
 * 在设置代码的时候,在方法的入参中传入了父类接口的引用.
 * 
 */
public class FactoryTest {
	/*
	 * 多继承,隐藏具体的实现,方便修改实现不会影响使用
	 * */
	public static void factory(CycleFactory cFactory){
		Cycle cycle = cFactory.getInstance();
		cycle.run();
	}
	
	public static void main(String[] args) {
		factory(new UnicycleFactory());
		factory(new BicycleFactory());
	}
}
/*1.人力车的抽象接口
 * 
 * */
interface Cycle{
	public void run();
}
/*
 * 实现人力车接口->单轮车
 */
class Unicycle implements Cycle{

	@Override
	public void run() {
		System.out.println("-　独轮车　-");
	}
}
/*
 * 实现人力车接口->脚踏车
 */
class Bicycle implements Cycle{

	@Override
	public void run() {
		System.out.println("=　脚踏车　=");
	}
}
/*
 * 实现人力车接口->三轮车
 */
class Tricycle implements Cycle{

	@Override
	public void run() {
		System.out.println("三　三轮车　三");
	}
}
/*
 *生产人力车工厂抽象接口,对于不同的工厂具有不同的实现 
 * */
interface CycleFactory{
	Cycle getInstance();
}
/*
 * 实际工厂->单轮车工厂
 * */
class UnicycleFactory implements CycleFactory{

	@Override
	public Cycle getInstance() {
		return new Unicycle();
	}
}
/*
 * 实际工厂->脚踏车工厂
 * */
class BicycleFactory implements CycleFactory{

	@Override
	public Cycle getInstance() {
		return new Bicycle();
	}
}
/*
 * 实际工厂->三轮车工厂
 * */
class TricycleFactory implements CycleFactory{

	@Override
	public Cycle getInstance() {
		return new Tricycle();
	}
}
