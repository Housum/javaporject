package com.hotusm.security;

import java.io.File;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
/**
 * 
 * JAVA��Ȩ����
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
		//�����̵߳���Ȩ���ݸ����̵߳���Ȩ  (���̲߳���̳и��̵߳���Ȩ  ���ǽ���Ȩ���մ�����ȥ)
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
