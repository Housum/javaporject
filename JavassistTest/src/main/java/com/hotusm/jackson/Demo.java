package com.hotusm.jackson;

public class Demo {
	
	private String name;
	private String remark;
	
	public Demo() {
		super();
	}

	public Demo(String name,String remark) {
		super();
		this.name = name;
		this.remark=remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
