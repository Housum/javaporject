package com.hotusm.first;

/**
 * 
 * @author Hotusm
 * 1.测试枚举的使用
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
	
	/*1.enum使用场景*/
	public static void describe(Color color){
		
		switch(color){
		
			case BULE:System.out.println("颜色是蓝色");break;
			case RED:System.out.println("颜色是红色");break;
			case BLACK:System.out.println("颜色是黑色");break;
			default:
		}
	}
}
