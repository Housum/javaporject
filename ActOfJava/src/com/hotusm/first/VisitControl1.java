package com.hotusm.first;

/**
 * 
 * @author Hotusm
 * 1.����Ȩ��
 */
public class VisitControl1 {
	
	public static void main(String[] args) {
		/*1.���Է���Ȩ��,Ĭ�ϵķ���Ȩ���ǰ������,ֻ����ͬ�İ�����Ĳ��ܹ�����*/
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
