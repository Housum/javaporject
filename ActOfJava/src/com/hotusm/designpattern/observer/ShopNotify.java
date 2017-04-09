package com.hotusm.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ShopNotify{
	
	private final List<Observer> interest=new ArrayList<Observer>();
	
	public void priceDrops(){
		
		for(Observer ob:interest){
			ob.update("price drop");
		}
	}
	public void addInterest(Observer ob){
		interest.add(ob);
	}
}
