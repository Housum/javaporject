package com.hotusm.array;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * http://blog.csdn.net/yinwenjie/article/details/51818352
 *  ����һ���̰߳�ȫ��List  ��ÿһ�ε��޸Ĳ�����ʱ�� 
 *  ������������Ԫ�ص��޸Ĳ���ʱ���������Ƚ������е�ԭ��Ԫ�ؿ�¡��һ�����������У�
 *  Ȼ��Ը��������е�Ԫ�ؽ����޸Ĳ���������Щ������ɺ��ٽ������е�Ԫ�ؼ�������
 *  ��д��ԭ�е���������������޸Ĳ���
 *
 */
public class CopyOnWriteArrayListTest {
	
	public static void main(String[] args) {
		
		List<Object> list=new CopyOnWriteArrayList<Object>();
	}
}
