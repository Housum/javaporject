package com.hotusm.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DemoEntity {
	
	 String id;
	 String code;
	public String getId() {
		return id;
	}
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	@XmlElement
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
