package com.hotusm.designModel.factory.abstrac;

import com.hotusm.designModel.factory.Pizza;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月9日   <br/>
 * @description 
 * 1.现在考虑到了每一个人自己需要定义自己风格的Pizza  而不是
 * 先定义好了的类 。 这时候需要进行解耦  但从另外的一层面来看我们希望无论Pizza怎么改变  但是有一点不会
 * 变  就是
 * 2.工厂方法的最终思考的就是将创建实体的过程给抽象出来
 * 整个流程不会变    
 * @see NYPizzaStore
 * @see MexicoPizzaStore 
 * @see org.springframework.web.servlet.ViewResolver 这个是典型的应用场景
 * 
 */
public abstract class PizzaStore {

	public Pizza orderPizza(String type){
		Pizza pizza=null;
		
		/**
		 * 将创建过程解耦
		 */
		pizza=createPizza(type);
		
		/**
		 * 定义了基本的框架流程  
		 */
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	};
	
	/**
	 * 
	 * @description 子类进行继承<br/> 
	 * @param type
	 * @return
	 */
	public abstract Pizza createPizza(String type);
	
}
