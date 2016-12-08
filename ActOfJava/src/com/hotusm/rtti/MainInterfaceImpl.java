package com.hotusm.rtti;

public class MainInterfaceImpl implements MainInterface{

	public static void main(String[] args) {
		
		boolean b = MainInterface.class.isAssignableFrom(MainInterfaceImpl.class);
		System.out.println(b);
		
		boolean b2 = MainInterfaceImpl.class.isAssignableFrom(MainInterface.class);
		System.out.println(b2);
		
		/**
		 * output:
		 * true
		 * false
		 */
		
	}
}
