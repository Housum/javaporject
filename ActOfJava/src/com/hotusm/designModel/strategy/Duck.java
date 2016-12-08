package com.hotusm.designModel.strategy;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��19��   <br/>
 * @description  ����ģʽ  �����ڱ仯���ǲ����㷨������� ��Ϊ�㷨�� ���Զ�̬���л��㷨
 * ���ԭ��:
 *  1.����ӿڱ�� ������ʵ�ֱ��
 *  2.����Ƶ�ʱ��  �������ı�Ĳ��ֳ������  ���������ں����ĸĶ���  ����Ӱ��
 *  �ǲ��ֲ������Ķ��Ĵ���
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
