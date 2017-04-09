package com.hotusm.io.learn.string;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * �ַ���ȡ���ܵ�
 * 1.PipedReader(PipedWriter src, int pipeSize)  ��һ������Ϊ����ܵ��� �ڶ�������Ϊ�ܵ��Ĵ�С
 * ���û�еڶ���������ôĬ�ϵĹܵ���СΪ1024
 * 2.PipedReader() û�в����Ĺ��캯��Ϊ��û��ʼ����״̬  ��Ҫ����connect(PipedWriter src)
 * 3.read() ����ж��� ֪�������������� Ȼ���ڽ��ж�ȡ
 */
public class PipedReaderTest {

	public static void main(String[] args) throws Exception{
		testPipedReaderConnect();
	}
	public static void testPipedReaderConnect() throws Exception{
		PipedReader pipedReader=new PipedReader();
		final PipedWriter writer=new PipedWriter();
		//���ַ�ʽ��������
		pipedReader.connect(writer);
		char[] buff=new char[10];
		new Thread(()->{
			try {
				//ͣ��������֮��
				Thread.sleep(3000);
				writer.write("hello piped");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}).start();
		pipedReader.read(buff);
		System.out.println(buff);
	}

}
