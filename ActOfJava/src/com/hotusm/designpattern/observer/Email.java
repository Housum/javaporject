package com.hotusm.designpattern.observer;

public class Email implements Observer{

	@Override
	public void update(Object obj) {
		
		System.out.println(obj);
	}
	
}
