package com.hotusm.designpattern.decorator;

import java.math.BigDecimal;
/*
 * Espresso(Å¨Ëõ)
 * */
public class Espresso extends Beverage{

	public Espresso(){
		
		desc="Espresso";
	}
	
	@Override
	public BigDecimal cost() {
		
		return new BigDecimal("0.99");
	}
}
