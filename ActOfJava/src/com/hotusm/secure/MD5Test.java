package com.hotusm.secure;

import java.security.MessageDigest;
/**
 * MD5 ��ϢժҪ���� 
 * Ϊһ����Ϣ����Ψһ��һ��ֵ ��������ظ�
 * 
 */
public class MD5Test {
	
	public static void main(String[] args) throws Exception {
//		byte[] encryptMD5 = encryptMD5("123".getBytes());
//		//һ����������Ὣmd5��ֵBase64һ��
//		byte[] b = Base64Test.encryptBASE64(encryptMD5);
//		System.out.println(new String(b));
//		
		MessageDigest md5 = MessageDigest.getInstance("MD5");  
	    md5.update("111".getBytes());
	    byte[] digest = md5.digest();
	    System.out.println(new String(digest));
	    System.out.println("---------------------------");
	    md5.reset();
	    md5.update("222".getBytes());
	    md5.update("111".getBytes());
	    byte[] digest2 = md5.digest();
	    String username="hotusm";
	    String password="1234";
	    md5.reset();
	    
	    md5.update(username.getBytes());
	    md5.update(password.getBytes());
	    
	    System.out.println(new String(digest2));
		
	}
	
	public static byte[] encryptMD5(byte[] data) throws Exception {  
		  
	    MessageDigest md5 = MessageDigest.getInstance("MD5");  
	    md5.update(data);
	    return md5.digest();  
	}
}
