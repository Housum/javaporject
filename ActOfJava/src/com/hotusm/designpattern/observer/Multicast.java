package com.hotusm.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Multicast implements InterestEvent{
	
	private final List<Observer> obervers=new ArrayList<>();
	
	private Object news;
	
	@Override
	public void registerOberver(Observer oberver) {
	
		obervers.add(oberver);
	}

	@Override
	public void remove(Observer oberver) {
		
		obervers.remove(oberver);
	}
	
	protected void notifyAllObserver(){
		
		for(Observer observer:obervers){
			
			observer.update(getNews());
		}
	}
	
	public void hasNews(Object object){
		setNews(object);
		notifyAllObserver();
	}

	public void setNews(Object news) {
		this.news = news;
	}

	public Object getNews() {
		return news;
	}
	
	
	
}
