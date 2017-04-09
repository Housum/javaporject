package com.hotusm.thread.detail.book.atomic;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 
 * 1.����һ��ԭ����
 * ʹ�ò�������final ��ô�����̰߳�ȫ��
 * ע����Ϊ�����������Ȼ����  ��������Ķ����ǿ��Ըı�� 
 * ��������ʹ����Arrays.copyOf����  �����Ͳ�����ֶ�������������
 * 2.��������þ�����ΪServlet��һ����ʾ�ֽ��  ����������Ƕ��̵߳�
 * ������Ҫ���ǵ��ɼ���  ���������  ���Ҷ���Ҳ����ʧЧ
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
			//������  ����ÿ�η��ʵ��� ������ֱ�ӽ����������ȥ  
			//��Ϊ���������ڶ��еĶ���  ������������Ȼ���ܱ�  ���Ƕ������ܸı�
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
		
	}
}
