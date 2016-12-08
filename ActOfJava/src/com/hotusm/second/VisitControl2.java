package com.hotusm.second;

import com.hotusm.first.VisitControl;

/**
 * @author Hotusm
 * 1.访问权限控制
 */
public class VisitControl2 {
	
	public static void main(String[] args) {
		VisitControl v=new VisitControl();
		int m = v.m;
		v.testPublic();
		
	}
}
