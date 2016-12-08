package com.hotusm.secure;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HexTest {
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * Hex编码.
	 * 利用Hex编码的是字节长度的两倍
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}
}
