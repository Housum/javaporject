package com.hotusm.designModel.decorator;

import java.math.BigDecimal;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��20��   <br/>
 * @description װ����ģʽ  ��ԭ�е�����������һЩ�߼�
 */
public class Mocha extends CondimentDecorator{
	
	private Beverage beverage;
	
	public Mocha(Beverage beverage){
		
		this.beverage=beverage;
	}

	@Override
	public String getDesc() {
		
		return this.beverage.getDesc()+",Mocha";
	}

	@Override
	public BigDecimal cost() {
		
		return this.beverage.cost().add(new BigDecimal("0.10"));
	}

}
