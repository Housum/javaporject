package com.hotusm.jmx.test.controller;

public interface ControllerMBean {

	//����
	public void setName(String name);
	public String getName();
	
    //����  
    /** 
     * ��ȡ��ǰ��Ϣ 
     * @return 
     */  
    public String status();  
    public void start();  
    public void stop();  
	
}
