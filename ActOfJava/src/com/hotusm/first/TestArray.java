package com.hotusm.first;

/**
 * 
 * @author Hotusm
 * 1.������������
 */
public class TestArray {
	
	public static void main(String[] args) {
		/*
		 * 1.�������������,���������ǺͶ���һ�����Ǵ���
		 * ���е�,ͨ����������ý��в���
		 */
		char[] array1={'a','b'};
		int hashCode1 = array1.hashCode();
		char[] array;
		array=array1;
		int hashCode = array.hashCode();
		System.out.println("hashCode:"+hashCode+" hashCode1"+hashCode1);
		array[0]='c';
		
		for(char a:array1){
			System.out.println(a);
		}
		/*2.���������ǻ������͵�����,�������������͵�����,������length�������*/
		int length=array.length;
		int length1=array1.length;
		System.out.println("length:"+length+" length1:"+length1);
		
		/*3.���԰�����Ȩ��,����û������*/
		VisitControl v=new VisitControl();
		int i = v.i;
	}
}
