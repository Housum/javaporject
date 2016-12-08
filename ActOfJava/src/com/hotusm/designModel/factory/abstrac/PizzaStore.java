package com.hotusm.designModel.factory.abstrac;

import com.hotusm.designModel.factory.Pizza;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��9��   <br/>
 * @description 
 * 1.���ڿ��ǵ���ÿһ�����Լ���Ҫ�����Լ�����Pizza  ������
 * �ȶ�����˵��� �� ��ʱ����Ҫ���н���  ���������һ������������ϣ������Pizza��ô�ı�  ������һ�㲻��
 * ��  ����
 * 2.��������������˼���ľ��ǽ�����ʵ��Ĺ��̸��������
 * �������̲����    
 * @see NYPizzaStore
 * @see MexicoPizzaStore 
 * @see org.springframework.web.servlet.ViewResolver ����ǵ��͵�Ӧ�ó���
 * 
 */
public abstract class PizzaStore {

	public Pizza orderPizza(String type){
		Pizza pizza=null;
		
		/**
		 * ���������̽���
		 */
		pizza=createPizza(type);
		
		/**
		 * �����˻����Ŀ������  
		 */
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	};
	
	/**
	 * 
	 * @description ������м̳�<br/> 
	 * @param type
	 * @return
	 */
	public abstract Pizza createPizza(String type);
	
}
