package com.hotusm.designpattern.adapter;

public class MallardDuck implements Duck{

	@Override
	public void quack() {
		
		System.out.println("mallardDuck quack");
	}

	@Override
	public void fly() {
		
		System.out.println("mallardDuck fly");
	}

}
