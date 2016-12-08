package com.hotusm.first;

/**
 * 
 * @author Hotusm
 * 1.访问权限
 */
public class VisitControl1 {
	
	public static void main(String[] args) {
		/*1.测试访问权限,默认的访问权限是包级别的,只有相同的包下面的才能够访问*/
		VisitControl v=new VisitControl();
		v.testControll();
		
		/*2.protected*/
		int j = v.j;
		v.testProtected();
		/*3.public */
		int m = v.m;
		v.testPublic();
		
	}
}
