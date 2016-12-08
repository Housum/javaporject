package com.hotusm.first;

/**
 * 
 * @author Hotusm
 * 1.����ö�ٵ�ʹ��
 */
public class Enum {
	
	enum Color{
		BULE,RED,BLACK
	}
	
	public static void main(String[] args) {
		/*1.*/
		Color bule = Color.RED;
		describe(bule);
		/*2.*/
		for(Color c:Color.values()){
			System.out.println(c+" ordinal:"+c.ordinal());
		}
	}
	
	/*1.enumʹ�ó���*/
	public static void describe(Color color){
		
		switch(color){
		
			case BULE:System.out.println("��ɫ����ɫ");break;
			case RED:System.out.println("��ɫ�Ǻ�ɫ");break;
			case BLACK:System.out.println("��ɫ�Ǻ�ɫ");break;
			default:
		}
	}
}
