package com.hotusm.designModel.strategy;

public class DuckSimulate {
	
	public static void main(String[] args) {
		
		QuackBehavior behavior=new GuaGuaQuackBehavior();
		FlyBehavior flyBehavior=new FasterFlyBehavior();
		Duck duck=new GreenHeadDuck();
		
		//Ŀǰ��ͷѼ����Ϊ��:���ɽ�+���ٷ�
		duck.setQuackBehavior(behavior);
		duck.setFlyBehavior(flyBehavior);
		duck.performQuack();
		duck.performFly();
		
		QuackBehavior behavior2=new GeGeQuackBehavior();
		FlyBehavior flyBehavior2=new SlowFlyBeHavior();
		
		//ʹ�ö�̬  ����ͷѼ����Ϊ����:������+�ɵ���
		duck.setQuackBehavior(behavior2);
		duck.setFlyBehavior(flyBehavior2);
		
		duck.performQuack();
		duck.performFly();
		
		Duck duck2=new RubberDuck();
		FlyBehavior behavior3=new NotFlyBehavior();
		QuackBehavior behavior4=new NotQuackBehavior();
		
		//��ƤѼ�����Ҳ�����  ���������Ҫ�����ƤѼ����Ϊ�Ļ� ��ô��ֱ��ʵ��ĳһ����ĳ�������OK��
		duck2.setFlyBehavior(behavior3);
		duck2.setQuackBehavior(behavior4);
		
	}
}
