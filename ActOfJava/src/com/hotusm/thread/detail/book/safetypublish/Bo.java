package com.hotusm.thread.detail.book.safetypublish;

public class Bo implements Cloneable{
	
	public String msg;

	public Bo() {
		super();
	}
	public Bo(Bo bo){
		this.msg=bo.getMsg();
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
