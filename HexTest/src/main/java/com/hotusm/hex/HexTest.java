package com.hotusm.hex;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 
 * 
 * @author Hotusm
 * @since 2016-03-17
 * 
 * HexµÄ²âÊÔ
 */
public class HexTest {
	
	public static void main(String[] args) {
		char[] encodeHex = Hex.encodeHex("Hello World".getBytes());
		System.out.println(encodeHex);
		try {
			byte[] dh = Hex.decodeHex(encodeHex);
			System.out.println(new String(dh));
		} catch (DecoderException e) {
			e.printStackTrace();
		}
	}
}
