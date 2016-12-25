package com.hotusm.guavaex.hash;

import java.nio.charset.Charset;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

import junit.framework.TestCase;
/**
 * http://ifeve.com/google-guava-hashing/
 * 
 * Hashing提供了一系列的散列算法
 * md5()	murmur3_128()	murmur3_32()	sha1()
   sha256()	sha512()	goodFastHash(int bits)	
 */
public class HashTest extends TestCase{
	
	//Hashing类提供了若干散列函数，以及运算HashCode对象的工具方法。
	@Test
	public void test_hash(){
		HashFunction hf = Hashing.md5();
		HashCode hc = hf.newHasher()
		        .putLong(10)
		        .putString("hotusm", Charsets.UTF_8)
		        .putObject(new Person("1", "lu", "qibao", 93), 
		        		new Funnel<Person>() {

							public void funnel(Person from, PrimitiveSink into) {
								into
								.putString(from.getId(), Charsets.UTF_8)
								.putString(from.getFirstName(), Charsets.UTF_8)
								.putString(from.getLastName(), Charsets.UTF_8)
								.putInt(from.getBirthYear());
							}
						})
		        .hash();
		
		long l = hc.asLong();
//		hc.writeBytesTo(dest, offset, maxLength)

	}
}
