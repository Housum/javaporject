package com.hotusm.sun.misc;

public class PrivateClass {
	
	private String name;

	private PrivateClass(){}
	
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "name:"+name;
	}
}
