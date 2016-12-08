package com.hotusm.test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Customer {
	
	private Integer id;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Customer(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Customer() {
		super();
	}
	
	public static void main(String[] args) {
		//protected 
		//new Prime().num;
		//private 
		//new Prime().num1;
		//default
		//new Prime().num2;
		//public
		String num3 = new Prime().num3;
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(null, "2");
		
		//hashtable not allow null into key or value
		Map<String,Object> hashTable=new Hashtable<String,Object>();
		hashTable.put(null, "1");
	}
	
}
