package com.hotusm.first;

//import static net.mindview.util.Print.*;
public class StaticImport {
	
	public static void main(String[] args) {
		int i=-1;
		System.out.println(Integer.toBinaryString(i));
		i>>>=10;
		System.out.println(Integer.toBinaryString(i));
	}
}
