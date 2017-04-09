package com.hotusm.designpattern.factory;

public class PizzaStore {
	
	private SimplePizzaFactory pizzaFactory;
	
	public PizzaStore(SimplePizzaFactory pizzaFactory){
		
		this.pizzaFactory=pizzaFactory;
	}
	
	/**
	 * 
	 * @description OO设置原则  将不变的和经常改变的分离开<br/> 
	 * @param type
	 * @return
	 */
	public Pizza orderPizze(String type){
		Pizza pizza=null;
		
		/***
		 * 这样的话  在很多地方需要使用到Pizza具体实现的地方 都可以共用了
		 * 假设没有Factory的话  那么就只能每一次都写这样的一段代码
		 */
		pizza=pizzaFactory.createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
	public static void main(String[] args) {
		Pizza orderPizze = new PizzaStore(new SimplePizzaFactory()).orderPizze("cheese");
		
		System.out.println(orderPizze.getClass());
	}
}
