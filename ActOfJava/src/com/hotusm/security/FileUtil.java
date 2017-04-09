package com.hotusm.security;

import java.io.File;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
/**
 * 
 * JAVA授权控制
 *
 */
public class FileUtil{
	
	private static final String FILE_PATH="D://";

	public static void makeFile(String fileName){
		
		try {
			File fs=new File(FILE_PATH+fileName);
			fs.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void doPrivilegedAction(final String fileName){
		//将本线程的授权传递给子线程的授权  (子线程不会继承父线程的授权  除非将授权快照传递下去)
		AccessControlContext acc = AccessController.getContext();
		AccessController.doPrivileged(new PrivilegedAction<String>() {

			@Override
			public String run() {
				makeFile(fileName);
				return null;
			}
		},acc);
	}
}
