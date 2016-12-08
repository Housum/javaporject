package com.hotusm.security;


public class Main {
	
	public static final String FILE_PATH="D:\\acces\\";
	public static void main(String[] args) {
		FileUtil.doPrivilegedAction("b.txt");
		
	}
}
