package com.hotusm.thread.detail.book.cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger>{

	@Override
	public BigInteger compute(String arg) {
		BigInteger bNumber=new BigInteger(arg);
		return bNumber;
	}

}
