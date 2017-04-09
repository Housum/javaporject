package com.hotusm.designpattern.decorator;

import java.math.BigDecimal;

public abstract class Beverage {
	
	protected String desc="blank";
	
	public String getDesc() {
		
		return desc;
	}
	
	public abstract BigDecimal cost();
}
