package com.hotusm.jmx.test.controller;

public class Controller implements ControllerMBean{
	
	private String name;

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		return this.name;
	}

	public String status() {
		
		return "this is a Controller MBean'name is "+this.name;
	}

	public void start() {
		
	}

	public void stop() {
		
	}

}
