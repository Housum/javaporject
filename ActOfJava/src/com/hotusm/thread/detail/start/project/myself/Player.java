package com.hotusm.thread.detail.start.project.myself;
/**
 * 
 *�˶�Ա
 */
public class Player {
	
	public static Object LOCK_OBJECT=new Object();
	
	
	private String name;
	private String sex;
	/**
	 * �ܲ����ٶ�
	 */
	private double speed;
	
	/**
	 * �����ɼ�
	 */
	private Result resut;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Result getResut() {
		return resut;
	}
	public void setResut(Result resut) {
		this.resut = resut;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return speed;
	}
}
