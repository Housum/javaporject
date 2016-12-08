package com.hotusm.secure;

import java.util.Base64;
/**
 * BASE64加密和解密
 * 其实也不算是加密  就是一种编码的方式
 */
public class Base64Test {
	
	public static void main(String[] args) {
		byte[] b1 = encryptBASE64("1".getBytes());
		byte[] b2 = encryptBASE64("11111111111111111111".getBytes());
		System.out.println("b1"+new String(b1)+" b2:"+new String(b2));
		
	}
	
	public static byte[] decryptBASE64(byte[] value){
		
		return Base64.getDecoder().decode(value);
	}
	
	public static byte[] decryptBASE64(String value){
		
		return Base64.getDecoder().decode(value);
	}
	public static byte[] encryptBASE64(byte[] value){
		
		return Base64.getEncoder().encode(value);
	}
	
}
