package com.hotusm.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//表示的是xml元素
@XmlRootElement
public class Customer {

	String name;
	int age;
	int id;
	
	public String getName() {
		return name;
	}
	//节点
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	//元素
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	
	
}
