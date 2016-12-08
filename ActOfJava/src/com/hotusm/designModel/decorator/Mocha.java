package com.hotusm.designModel.decorator;

import java.math.BigDecimal;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月20日   <br/>
 * @description 装饰者模式  在原有的类上面增加一些逻辑
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
