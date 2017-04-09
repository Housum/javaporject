package com.hotusm.designpattern.factory;

public class PizzaStore {
	
	private SimplePizzaFactory pizzaFactory;
	
	public PizzaStore(SimplePizzaFactory pizzaFactory){
		
		this.pizzaFactory=pizzaFactory;
	}
	
	/**
	 * 
	 * @description OO����ԭ��  ������ĺ;����ı�ķ��뿪<br/> 
	 * @param type
	 * @return
	 */
	public Pizza orderPizze(String type){
		Pizza pizza=null;
		
		/***
		 * �����Ļ�  �ںܶ�ط���Ҫʹ�õ�Pizza����ʵ�ֵĵط� �����Թ�����
		 * ����û��Factory�Ļ�  ��ô��ֻ��ÿһ�ζ�д������һ�δ���
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
