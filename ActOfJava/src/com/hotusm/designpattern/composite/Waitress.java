package com.hotusm.designpattern.composite;

import java.util.Iterator;

public class Waitress {

	MenuCompont compont;
	
	public Waitress(MenuCompont compont){
		this.compont=compont;
	}
	
	public void printMenu(){
		this.compont.print();
	}
	public void printVegetarinMenu(){
		
		Iterator iterator = this.compont.createIterator();
		System.out.println("VEGETARIN MENU:");
		
		while(iterator.hasNext()){
			MenuCompont component=(MenuCompont) iterator.next();
			try {
				if(component.isVegetarian()){
					component.print();
				}
			} catch (Exception e) {}

		}
	}
	public static void main(String[] args) {
		
		MenuCompont compont=new Menu("lunch", "enough");
		MenuCompont compont1=new Menu("dinner", "health");
		MenuItem item1=new MenuItem("steak", "delicious", false, 10);
		MenuItem item2=new MenuItem("cabbage", "health", true, 0.67);
		compont.add(item1);
		compont.add(item2);
		compont.add(compont1);
		Waitress waitress = new Waitress(compont);
		//waitress.printMenu();
		waitress.printVegetarinMenu();
		
		
		
	}
}
