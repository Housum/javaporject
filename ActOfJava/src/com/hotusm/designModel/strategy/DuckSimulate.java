package com.hotusm.designModel.strategy;

public class DuckSimulate {
	
	public static void main(String[] args) {
		
		QuackBehavior behavior=new GuaGuaQuackBehavior();
		FlyBehavior flyBehavior=new FasterFlyBehavior();
		Duck duck=new GreenHeadDuck();
		
		//目前绿头鸭的行为是:呱呱叫+快速飞
		duck.setQuackBehavior(behavior);
		duck.setFlyBehavior(flyBehavior);
		duck.performQuack();
		duck.performFly();
		
		QuackBehavior behavior2=new GeGeQuackBehavior();
		FlyBehavior flyBehavior2=new SlowFlyBeHavior();
		
		//使用多态  将绿头鸭的行为换成:咯咯叫+飞的慢
		duck.setQuackBehavior(behavior2);
		duck.setFlyBehavior(flyBehavior2);
		
		duck.performQuack();
		duck.performFly();
		
		Duck duck2=new RubberDuck();
		FlyBehavior behavior3=new NotFlyBehavior();
		QuackBehavior behavior4=new NotQuackBehavior();
		
		//橡皮鸭不会飞也不会叫  如果后期需要添加橡皮鸭的行为的话 那么就直接实现某一个或某几个类就OK了
		duck2.setFlyBehavior(behavior3);
		duck2.setQuackBehavior(behavior4);
		
	}
}
