package com.hotusm.designModel.factory;

public class SimplePizzaFactory {

	public Pizza createPizza(String type){
		Pizza pizza=null;
		
		if("cheese".equals(type))
			pizza=new CheesePizza();
		
		else if("pepperon".equals(type))
			pizza=new PepperonPizza();
			
		return pizza;
	}
}
