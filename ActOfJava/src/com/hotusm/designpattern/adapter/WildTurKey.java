package com.hotusm.designpattern.adapter;

public class WildTurKey implements TurKey{

	@Override
	public void gobble() {
		
		System.out.println("WildTurKey gobble");
	}

	@Override
	public void fly() {
		
		System.out.println("WildTurKey fly");
	}

}
