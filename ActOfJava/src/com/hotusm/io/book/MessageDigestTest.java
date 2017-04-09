package com.hotusm.io.book;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class MessageDigestTest {
	
	
	@Test
	public void messageDigest(){
		try {
			FileInputStream fis=new FileInputStream(new File("C://test.png"));
			MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
			
			DigestInputStream stream=new DigestInputStream(fis, messageDigest);
			while(stream.read()!=-1);
			stream.close();
			
			byte[] digest = messageDigest.digest();
			System.out.println(DatatypeConverter.printHexBinary(digest));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
