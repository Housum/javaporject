package com.hotusm.designpattern.observer;

public interface InterestEvent {

	public void registerOberver(Observer oberver);
	public void remove(Observer oberver);
	
}
