package com.hotusm.designModel.observer;

public interface InterestEvent {

	public void registerOberver(Observer oberver);
	public void remove(Observer oberver);
	
}
