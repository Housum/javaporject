package com.hotusm.io.learn.string;

import java.io.CharArrayReader;
import java.io.PushbackReader;

/**
 * PushbackReader �ܹ����������ַ����л��Ƶ� ��������. 1.PushbackReader(Reader reader,int size)
 * size: ÿһ�ε�read()�ĸ��� ,Ĭ����1 ͬʱҲ�ǻ������Ĵ�С 2.unread()���ط���
 * ��һ�����߶�����ַ����Ƶ���������,λ�þ�����һ�ζ����Ľ�β ���� abcd ����c �����Ƴ�gf ��ô����Ϊ abcgfd �´ζ��Ļ� �ͻ��
 * gf��ʼ�� 3.skip(long size) ����size ���ַ�
 */
public class FilterReaderAndPushbackReaderTest {

	public static void main(String[] args) {
		testFilterReaderUnreadSingleChar();
		System.out.println();
		testFilterReaderUnreadMutilChar();
		testFilterReaderSkip();
	}

	/**
	 * �����abcCd
	 */
	public static void testFilterReaderUnreadSingleChar() {
		String str = "abcd";
		try (CharArrayReader charArrayReader = new CharArrayReader(str.toCharArray());
				PushbackReader pushbackReader = new PushbackReader(charArrayReader);) {
			int c;
			while ((c = pushbackReader.read()) != -1) {
				System.out.print((char) c);
				// unread()���÷� ���ַ������Ƶ���������
				if (c == 'c') {
					pushbackReader.unread('C');
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �����abcdefFUCgUC
	 */
	public static void testFilterReaderUnreadMutilChar() {
		String str = "abcdefg";
		try (CharArrayReader charArrayReader = new CharArrayReader(str.toCharArray());
				PushbackReader pushbackReader = new PushbackReader(charArrayReader, 3);) {
			char[] byteArr = new char[3];
			// read������һֱ���빹�캯���еڶ��������е�
			while ((pushbackReader.read(byteArr)) != -1) {
				System.out.print(byteArr);
				// unread()���÷� ���ַ������Ƶ���������
				if (new String(byteArr).equals("def")) {
					// �ƻصĲ��ܴ��ڻ������� �������������ǹ��캯���ĵڶ�������
					pushbackReader.unread("FUC".toCharArray());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �����abcfg
	 */
	public static void testFilterReaderSkip() {
		String str = "abcdefg";
		try (CharArrayReader charArrayReader = new CharArrayReader(str.toCharArray());
				PushbackReader pushbackReader = new PushbackReader(charArrayReader, 3);) {
			char[] byteArr = new char[3];
			// read������һֱ���빹�캯���еڶ��������е�
			while ((pushbackReader.read(byteArr)) != -1) {
				System.out.print(byteArr);
				//�������ص�!!!
				pushbackReader.skip(2L);
				byteArr = new char[3];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
