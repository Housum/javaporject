package com.hotusm.designModel.factory.abstrac;

import com.hotusm.designModel.factory.CheesePizza;
import com.hotusm.designModel.factory.PepperonPizza;
import com.hotusm.designModel.factory.Pizza;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月9日   <br/>
 * @description  纽约风格的披萨
 */
public class NYPizzaStore extends PizzaStore{

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		
		if("cheese".equals(type))
			pizza=new CheesePizza();
		
		else if("pepperon".equals(type))
			pizza=new PepperonPizza();
		
		return pizza;
	}

}
