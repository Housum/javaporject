package com.hotusm.designModel.strategy;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月19日   <br/>
 * @description  策略模式  将易于变化的那部分算法抽离出来 作为算法簇 可以动态的切换算法
 * 设计原则:
 *  1.面向接口编程 不面向实现编程
 *  2.在设计的时候  将经常改变的部分抽离出来  这样我们在后来的改动中  不会影响
 *  那部分不经常改动的代码
 *  
 */
public abstract class Duck {
	
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;
	
	
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	public void setQuackBehavior(QuackBehavior quackBehavior) {
		this.quackBehavior = quackBehavior;
	}
	
	public void performFly(){
		
		if(flyBehavior==null)
			return;
		
		this.flyBehavior.fly();
	}
	public void performQuack(){
		if(quackBehavior==null)
			return;
		this.quackBehavior.quack();
	}
	
	public abstract void display();
}
