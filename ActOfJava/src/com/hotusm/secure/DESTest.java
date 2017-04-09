package com.hotusm.secure;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * 对称加密  DES  
 * 需要有key才能进行加密和解密
 */
public class DESTest {
	
	private static final String ALGORITHM="DES";
	
	public static void main(String[] args) throws Exception {
		byte[] key = initKey();
		System.out.println("key："+new String(key));
		byte[] encrypt = encrypt("1234".getBytes(), key);
		System.out.println(new String(encrypt));
		byte[] decrypt = decrypt(encrypt, key);
		System.out.println(new String(decrypt));
	}
	
	public static Key toKey(byte[] key) throws Exception{
		DESKeySpec spec=new DESKeySpec(key);
		SecretKeyFactory factory=SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretkey = factory.generateSecret(spec);
		return secretkey;
	}
	
	public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
		Key k=toKey(Base64Test.decryptBASE64(key));
		Cipher cipher=Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	public static byte[] encrypt(byte[] data,byte[] key) throws Exception{
		Key k=toKey(Base64Test.decryptBASE64(key));
		Cipher cipher=Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	public static byte[] initKey() throws NoSuchAlgorithmException{

		return initKey(null);
	}
	public static byte[] initKey(byte[] seed) throws NoSuchAlgorithmException{
		SecureRandom secureRandom=null;
		if(seed!=null){
			secureRandom=new SecureRandom(Base64Test.encryptBASE64(seed));
		}else{
			secureRandom=new SecureRandom();
		}
		
		KeyGenerator kg=KeyGenerator.getInstance(ALGORITHM);
		kg.init(secureRandom);
		
		SecretKey key=kg.generateKey();
		return Base64Test.encryptBASE64(key.getEncoded());
	}
}
