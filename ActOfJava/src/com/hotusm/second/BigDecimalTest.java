package com.hotusm.second;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.hotusm.thread.detail.start.Log;
/**
 * ��Ϊһ��Ļ������;���������  ���ǿ���ʹ��BigDecimal
 * �����н�����Щ�߾��ȵĲ���
 * 
 *
 */
public class BigDecimalTest {
	
	public static void main(String[] args) {
		/**
		 * ���캯���Ĳ���������int float String������  
		 * 
		 */
		BigDecimal bd=new BigDecimal(10);
		BigDecimal bd1=new BigDecimal(7);
		/**
		 * �ڶ���������ʾ���Ǿ���(Ĭ����0) 
		 * ���ǳ������ȵĻ�  ��ô������󾫶�
		 * ����Ľ����1.4285715  �������ǿ��Դ��Ͼ���
		 * ����Ľ����:1.43
		 * ������������ʾ������ȥ��������ֵĲ���
		 * UP:����ֵ��һ��						1.2->2    -1.2->-2
		 * DOWM:����ֵ��һ��                        			1.2->1	  -1.2->-1
		 * CEILING:��������£  					1.2->2    -1.1->-1
		 * FLOOR:��������£       					1.2->1	  -1.1->-2
		 * HALF_UP:����5��ʱ���һ      				1.3->1 	   1.5->2  -1.5->-2 
		 * HALF_DOWM:����5����һ				 	
		 * 
		 */
		BigDecimal divide = bd.divide(bd1,2,RoundingMode.HALF_UP);
		/**
		 * �����ù�BigDecimal.intValue()������BigDecimal.floatValue()
		 * �õ���ͬ�Ľ��
		 */
		Log.info(divide.floatValue()+"");
	}
	
}
