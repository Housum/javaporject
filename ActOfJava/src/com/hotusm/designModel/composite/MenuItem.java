package com.hotusm.designModel.composite;

import java.util.Iterator;
/**
 * @author Hotusm  <br/>
 * @date 2016年10月17日   <br/>
 * @description 菜单项
 */
public class MenuItem extends MenuCompont{

	private String name;
	private String description;
	private boolean vegetarian;
	private double price;
	
	public MenuItem(String name, String description, boolean vegetarian, double price) {
		super();
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void print() {
		
		System.out.println(" "+getName());
		if(isVegetarian()){
			System.out.println("(v)");
		}
		System.out.println(" ,"+getPrice());
		System.out.println("   --"+getDescription());
	}

	@Override
	public Iterator createIterator() {
		
		return new NullIterator();
	}
	
}
