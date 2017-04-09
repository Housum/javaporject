package com.hotusm.designpattern.factory;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��9��   <br/>
 * @description  �����Ľӿ�  ��Ϊ���кܶ಻ͬ��ζ������ 
 * 
 * @see CheesePizza 
 * @see PepperonPizza
 * @see SimplePizzaFactory
 * @see PizzaStore
 * 
 */
public interface Pizza {

	public void prepare();
	
	public void bake();
	
	public void cut();
	
	public void box();
}
