package com.hotusm.bit;

import java.util.Random;

import org.junit.Test;

/**
 * 1.因为使用的很少 所以不知道 位操作优先级是最低的
 * @link http://baike.baidu.com/link?url=mXCDZyM2jhEvmBuUTTA6ZI45pB7mKqBFh3_MWZoz1b-7zcmGs_-RLgalD9fyEZd9QwN4yH9Zn-M8DWIEgvgf5q
 * 位运算其实就是将二进制数进行运算  对于二进制位数不够的前面添0
 * 例如:6二进制位110  11的二进制1011 那么这两个做运算的时候  就为0110 和1011之间的运算
 * 其中and运算:
 * 		0110
 * 		1011
 *结果          0010
 *就是二进制相同的位数进行并(&)运算
 *
 */
public class BitOperation {

	
	@Test
	public void orTest(){
		int temp=new Random().nextInt(100);
		//因为1的二进制就是1前面都为0  或运算其实就是最后以为变成1(因为另外一个数最后无论
		//是0或者1 或运算都为1)  然后再减一  那么结果无论如何都为偶数了 
		System.out.println((temp|1)-1);
	}
	@Test
	public void andTest(){
		int temp=new Random().nextInt(100);
		//和或操作相似 1的二进制前面都是0  所以&1的结果是取另外一个数的二进制最后一位
		System.out.println(temp&1);
	}
	
	/**
	 * 异或操作  
	 *  表示的是二进制同步运算  不同则为1  否则为0
	 *  还是上面的例子:
	 *  	6  0110
	 *      11 1011
	 *结果:  13 1101
	 */
	@Test
	public void xorTest(){
		
		int encode=6^11;
		int decode=encode^11;
		
		System.out.println("encode:"+encode+" decode:"+decode);

		int a=1,b=2;
		
		a=a^b;
		b=a^b;
		a=a^b;
		
		
		System.out.println("a:"+a+" b:"+b);
	}
	
	/**
	 * 每一位都取反
	 */
	@Test
	public void notTest(){
		System.out.println(~100);
	}
	
	/**
	 * 左移 就是将二进制想左移动 末尾添加0  
	 * 比如:
	 * 	2>>2 就是将二进制10后边加两个0 变为1000
	 * 
	 */
	@Test
	public void shlTest(){
		System.out.println(2<<2);
	}
	/**
	 * 右移  
	 *  和上面的相反  是将数的二进制向右移动  就是截取位数
	 *  比如 15>>1  1111变成111
	 */
	@Test
	public void shrTest(){
		System.out.println(15>>1);
	}
	@Test
	public void test(){
		System.out.println(0x7fffffff);
	}
	
	public void BitOperation(){
		
	}
	
	public static void main(String[] args) {
		new BitOperation().BitOperation();
	}
}
