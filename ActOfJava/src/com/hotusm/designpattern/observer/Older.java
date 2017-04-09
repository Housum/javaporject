package com.hotusm.designpattern.observer;

public class Older implements Observer{

	@Override
	public void update(Object obj) {
		
		System.out.println("¼àÌıµ½ÁË"+obj);
	}

}
