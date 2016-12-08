package com.hotusm.entity;

import com.hotusm.annctation.Constraints;
import com.hotusm.annctation.DBTable;
import com.hotusm.annctation.SQLFloat;
import com.hotusm.annctation.SQLString;

@DBTable
public class BooK {
	@SQLString(value=64,constraints=@Constraints(allowNull=true,primaryKey=true))
	private String id;
	@SQLFloat
	private float price;
	@SQLString(constraints=@Constraints(allowNull=true))
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
