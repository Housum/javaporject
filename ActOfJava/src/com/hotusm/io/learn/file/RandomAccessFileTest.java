package com.hotusm.io.learn.file;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * �����ʵ��֧�ֶ���������ļ��Ķ�ȡ��д�롣��������ļ�����Ϊ���ƴ洢���ļ�ϵͳ�е�һ������ byte ����
 * ������ָ�����������Ĺ�����������Ϊ�ļ�ָ�룻����������ļ�ָ�뿪ʼ��ȡ�ֽڣ������Ŷ��ֽڵĶ�ȡ��ǰ
 * �ƴ��ļ�ָ�롣�����������ļ��Զ�ȡ/д��ģʽ���������������Ҳ���ã�����������ļ�ָ�뿪ʼд���ֽڣ�
 * �����Ŷ��ֽڵ�д���ǰ�ƴ��ļ�ָ�롣д����������ĵ�ǰĩβ֮�������������¸�������չ�����ļ�ָ���
 * ��ͨ�� getFilePointer ������ȡ����ͨ�� seek �������á� 
 * ͨ������������е����ж�ȡ�����ڶ�ȡ�����������ֽ�֮ǰ�ѵ����ļ�ĩβ�����׳� EOFException����һ�� IOException�����������ĳЩԭ���޷���ȡ�κ��ֽڣ��������ڶ�ȡ�����������ֽ�֮ǰ�ѵ����ļ�ĩβ�����׳� IOException�������� EOFException����Ҫ�ر�ָ�����ǣ�������ѱ��رգ�������׳� IOException�� 
 * 
 * public RandomAccessFile(File file,String mode)
 *  "r" ��ֻ����ʽ�򿪡����ý��������κ� write �������������׳� IOException��  
	"rw" ���Ա��ȡ��д�롣������ļ��в����ڣ����Դ������ļ���  
	"rws" ���Ա��ȡ��д�룬���� "rw"����Ҫ����ļ������ݻ�Ԫ���ݵ�ÿ�����¶�ͬ��д�뵽�ײ�洢�豸��  
	"rwd"   ���Ա��ȡ��д�룬���� "rw"����Ҫ����ļ����ݵ�ÿ�����¶�ͬ��д�뵽�ײ�洢�豸��  

 */
public class RandomAccessFileTest {
	
	public static void main(String[] args) throws Exception{
		testRandomAccessFile();
	}
	
	public static void testRandomAccessFile() throws Exception{
		RandomAccessFile accessFile=new RandomAccessFile(new File("src/f1.txt"), "r");
		/*
		 * ֱ���������±�Ϊ3���ַ� 
		 * �ļ�����:
		 * ABCDEFG
		 * */
		accessFile.seek(3);
		
		char c=(char)accessFile.read();
		System.out.println(c);
	}

	
}
