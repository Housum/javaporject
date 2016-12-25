package com.hotusm.third.rmi;

import java.io.Serializable;

public class DTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String content;
	
	
	public DTO() {
		super();
	}
	public DTO(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "DTO [id=" + id + ", content=" + content + "]";
	}
}
