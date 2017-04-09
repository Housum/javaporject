package com.hotusm.designpattern.composite;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月17日   <br/>
 * @description  菜单
 */
public class Menu extends MenuCompont{
	
	private ArrayList<MenuCompont> menus=new ArrayList<MenuCompont>();
	private String name;
	private String description;
	
	
	public Menu(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Menu(ArrayList<MenuCompont> menus, String name, String description) {
		super();
		this.menus = menus;
		this.name = name;
		this.description = description;
	}

	@Override
	public void add(MenuCompont compont) {
		
		menus.add(compont);
	}
	@Override
	public void remove(MenuCompont compont) {
		menus.remove(compont);
	}
	@Override
	public MenuCompont getChild(int i) {
		return menus.get(i);
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void print() {
		
		System.out.println("\n"+getName());
		System.out.println(","+getDescription());
		System.out.println("------------");
		Iterator<MenuCompont> iterator = menus.iterator();
		while(iterator.hasNext()){
			MenuCompont menu = iterator.next();
			menu.print();
		}
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Iterator createIterator() {
		return new CompositeIterator(menus.iterator());
	}
}
