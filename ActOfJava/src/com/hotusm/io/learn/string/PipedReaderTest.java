package com.hotusm.io.learn.string;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 字符流取出管道
 * 1.PipedReader(PipedWriter src, int pipeSize)  第一个参数为输入管道流 第二个参数为管道的大小
 * 如果没有第二个参数那么默认的管道大小为1024
 * 2.PipedReader() 没有参数的构造函数为还没初始化的状态  需要调用connect(PipedWriter src)
 * 3.read() 会进行堵塞 知道有数据流到达 然后在进行读取
 */
public class PipedReaderTest {

	public static void main(String[] args) throws Exception{
		testPipedReaderConnect();
	}
	public static void testPipedReaderConnect() throws Exception{
		PipedReader pipedReader=new PipedReader();
		final PipedWriter writer=new PipedWriter();
		//这种方式进行连接
		pipedReader.connect(writer);
		char[] buff=new char[10];
		new Thread(()->{
			try {
				//停留三秒钟之后
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
