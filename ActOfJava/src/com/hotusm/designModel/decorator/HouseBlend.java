package com.hotusm.designModel.decorator;

import java.math.BigDecimal;
/*
 * HouseBlend(×ÛºÏ¿§·È)
 * */
public class HouseBlend extends Beverage{
	
	public HouseBlend(){
		
		desc="HouseBlend";
	}

	@Override
	public BigDecimal cost() {
		
		return new BigDecimal("0.89");
	}

}
