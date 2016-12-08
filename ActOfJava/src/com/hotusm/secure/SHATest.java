package com.hotusm.secure;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * SHA 也是一种信息摘要技术
 * 比MD5更加的难以破解
 */
public class SHATest {
	
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		
		File f=new File("C:/test.png");
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(); 
			InputStream in=new FileInputStream(f);
			for(int c=in.read();c!=-1;c=in.read()){
				out.write(c);
			}
			byte[] byteArray = out.toByteArray();
			byte[] encryptSHA = encryptSHA(byteArray);
			byte[] encryptBASE64 = Base64Test.encryptBASE64(encryptSHA);
			System.out.println(new String(encryptBASE64));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	public static byte[] encryptSHA(byte[] value) throws NoSuchAlgorithmException{
		MessageDigest sha=MessageDigest.getInstance("SHA");
		sha.update(value);
		
		return sha.digest();
	}
	
	
}
