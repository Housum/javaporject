package com.hotusm.designpattern.factory;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月9日   <br/>
 * @description  披萨的接口  因为会有很多不同口味的披萨 
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
