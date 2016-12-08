package com.hotusm.designModel.factory.abstrac;

import com.hotusm.designModel.factory.CheesePizza;
import com.hotusm.designModel.factory.MexicoPizza;
import com.hotusm.designModel.factory.Pizza;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月9日   <br/>
 * @description 墨西哥风味的披萨
 */
public class MexicoPizzaStore extends PizzaStore{

	@Override
	public Pizza createPizza(String type) {
		Pizza pizza=null;
		
		if("mexico".equals(type))
			pizza=new MexicoPizza();
		
		else if("native".equals(type))
			pizza=new CheesePizza();
			
		return pizza;
	}
	
	
	public static void main(String[] args) {
		
		NYPizzaStore nyPizzaStore=new NYPizzaStore();
		
		Pizza orderPizza = nyPizzaStore.orderPizza("cheese");
		System.out.println(orderPizza.getClass());
		
		MexicoPizzaStore mexicoPizzaStore=new MexicoPizzaStore();
		
		Pizza orderPizza2 = mexicoPizzaStore.orderPizza("mexico");
		System.out.println(orderPizza2.getClass());
		
	}

}
