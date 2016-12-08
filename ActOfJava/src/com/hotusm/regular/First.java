package com.hotusm.regular;

import java.io.File;

import org.junit.Test;

import com.hotusm.annotation.Date;

/**
 *1.  ?表示可能有 0个或者1个
 *2.  \d表示的是数字(-?\\d表示数字前面 有可能有一个符号)
 *3.  +代表一个或者多个
 *4.  在需要插入\的时候需要使用\进行转义   或者如果要插入一个\d应该:\\d
 *    但是对于其他的没有特殊的需要  使用\\来进行转义 如:+在正则里面有特殊的含义
 *    所以 我们使用\\+来进行转义   那么现在就是普通的+
 *5.  |代表或者的意思  [abc]和a|b|c是相同的意思
 *6.  ()括号有着将表达式分组的效果  glad和good 可以这样表示  'g(la|dd)d'
 *7.  \W非单词字符   		\w代表一个单词字符[a-zA-Z0-9]
 *8.  如果直接是一个字符比如:a就是单纯a字符
 *9.  [x-y] 表示一个区间   :[A-Z]大写字母  [a-zA-Z]
 *10.  . 代表任意的字符(如果需要使用普通的形式   就是这样:\\.)
 *11.  && 并且的意思[a-z&&[hij]]表示的是a到z任何字符并且和hij其中一个
 *12.  ^ 表示一行的开始   例如：/^A/不匹配"an A,"中的’A’,但匹配"An A."中最前面的’A’.
 *13. \s  空白符  (空格,tab,换行,换页和回车)
 *14. \S  非空白符[^\s]
 *15  *   零次或者多次匹配前面的字符或者表达式：zo*可以匹配  z(o出现0次) zo(o出现一次) zoo(o出现二次)
 *		      等价于{0,}
 *16.  X{n}恰好n次X. X{n,}至少n次X.  X{n,m}至少n次最多m次X
 *17.  (?i)开启大小写功能  (?-i)关闭大小写功能
 */
public class First {
	
	@Test
	public void first(){
		System.out.println("123".matches("-?\\d+"));
		System.out.println("-123".matches("-?\\d+"));
		System.out.println("+123".matches("-?\\d+"));
		//使用()把这个逻辑括起来  
		System.out.println("+123".matches("(-|\\+)?\\d+"));
		//a后面表示多个单词字符
		System.out.println("abcd".matches("a\\w+"));
		System.out.println("1".matches("\\W"));
		//大写字母开头 .结尾
		System.out.println("Asgt5。".matches("[A-Z].+\\."));
		//邮箱
		System.out.println("295113757@163.com".matches(".+@\\w+\\.\\w+"));
		
	}
	
	@Date(value=20160601)
	@Test
	public void second(){
		//(?i)开启忽略大小写
		System.out.println("ABC".matches("(?i)aBc"));
		
		
	}
	
	@Test
	public void test(){
		System.out.println("/em/orderinfo/".matches("^/em/orderinfo/"));
	}
}
