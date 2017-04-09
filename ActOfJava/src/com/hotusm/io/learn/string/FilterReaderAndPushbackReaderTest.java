package com.hotusm.io.learn.string;

import java.io.CharArrayReader;
import java.io.PushbackReader;

/**
 * PushbackReader 能够将流或者字符进行回推到 缓冲区中. 1.PushbackReader(Reader reader,int size)
 * size: 每一次的read()的个数 ,默认是1 同时也是缓冲区的大小 2.unread()重载方法
 * 将一个或者多个的字符回推到缓冲区中,位置就是上一次读过的结尾 比如 abcd 读到c 现在推出gf 那么就是为 abcgfd 下次读的话 就会从
 * gf开始读 3.skip(long size) 跳过size 个字符
 */
public class FilterReaderAndPushbackReaderTest {

	public static void main(String[] args) {
		testFilterReaderUnreadSingleChar();
		System.out.println();
		testFilterReaderUnreadMutilChar();
		testFilterReaderSkip();
	}

	/**
	 * 输出：abcCd
	 */
	public static void testFilterReaderUnreadSingleChar() {
		String str = "abcd";
		try (CharArrayReader charArrayReader = new CharArrayReader(str.toCharArray());
				PushbackReader pushbackReader = new PushbackReader(charArrayReader);) {
			int c;
			while ((c = pushbackReader.read()) != -1) {
				System.out.print((char) c);
				// unread()的用法 将字符给回推到缓冲区中
				if (c == 'c') {
					pushbackReader.unread('C');
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出：abcdefFUCgUC
	 */
	public static void testFilterReaderUnreadMutilChar() {
		String str = "abcdefg";
		try (CharArrayReader charArrayReader = new CharArrayReader(str.toCharArray());
				PushbackReader pushbackReader = new PushbackReader(charArrayReader, 3);) {
			char[] byteArr = new char[3];
			// read方法会一直读入构造函数中第二个参数中的
			while ((pushbackReader.read(byteArr)) != -1) {
				System.out.print(byteArr);
				// unread()的用法 将字符给回推到缓冲区中
				if (new String(byteArr).equals("def")) {
					// 推回的不能大于缓冲区的 缓冲区就是我们构造函数的第二个参数
					pushbackReader.unread("FUC".toCharArray());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出：abcfg
	 */
	public static void testFilterReaderSkip() {
		String str = "abcdefg";
		try (CharArrayReader charArrayReader = new CharArrayReader(str.toCharArray());
				PushbackReader pushbackReader = new PushbackReader(charArrayReader, 3);) {
			char[] byteArr = new char[3];
			// read方法会一直读入构造函数中第二个参数中的
			while ((pushbackReader.read(byteArr)) != -1) {
				System.out.print(byteArr);
				//这里是重点!!!
				pushbackReader.skip(2L);
				byteArr = new char[3];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
