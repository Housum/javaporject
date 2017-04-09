package com.hotusm.thread.detail.book.atomic;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 
 * 1.构造一个原子类
 * 使用不变类型final 那么就是线程安全的
 * 注意因为数组的引用虽然不变  但是这里的对象是可以改变的 
 * 所以这里使用了Arrays.copyOf方法  这样就不会出现对象溢出的情况了
 * 2.这个类作用就是作为Servlet的一个因示分解的  这种情况下是多线程的
 * 所以需要考虑到可见性  对象不能溢出  并且对象也不能失效
 */
public class AtomicClazz {
	
	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;
	
	public AtomicClazz(BigInteger lastNumber, BigInteger[] lastFactors) {
		super();
		this.lastNumber = lastNumber;
		this.lastFactors=Arrays.copyOf(lastFactors, lastFactors.length);
	}
	
	public BigInteger[] getFactors(BigInteger i){
		if(lastNumber==null||!lastNumber.equals(i)){
			return null;
		}else{
			//很明显  这里每次访问的是 都不是直接将数组给返回去  
			//因为数组存的是在堆中的对象  就算是引用虽然不能变  但是对象还是能改变
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
		
	}
}
