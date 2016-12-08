package com.hotusm.spi.impl;

import com.hotusm.spi.api.BusinessInterface;

public class BusinessService implements BusinessInterface{

	@Override
	public void say() {
		System.out.println("hello spi");
	}

}
