package com.hotusm.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.junit.Test;

/**
 * 
 *ͨ��FileChannel��ByteBuffer���ж�NIO���в���
 *NIO����������ߵ�
 *
 */
public class ByteBufferTest {
	
	public static String FILENAME="D://nio.txt";

	/**
	 * ������д�뵽�ļ���  �������漰������  ����ͨ��FileChannel(�ܵ�) ByteBuffer(������)
	 * ���в�����
	 */
	@Test
	public void bytebuffer(){
		FileOutputStream os=null;
		try {
			os=new FileOutputStream(FILENAME);
			FileChannel channel = os.getChannel();
			channel.write(ByteBuffer.wrap("��ð�  NIO".getBytes()));
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void bytebufferread(){
		try {
			RandomAccessFile arf=new RandomAccessFile(FILENAME, "rw");
			FileChannel channel = arf.getChannel();
			ByteBuffer allocate = ByteBuffer.allocate(1024);
			channel.read(allocate);
			//׼��������  �Ա���Ϣ������write()��ȡ
			allocate.flip();
			PrintStream out = System.out;
			while(allocate.hasRemaining()){
				out.write(allocate.get());
			}
			out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bytebufferwrite() throws Exception{
		FileChannel
			in= new FileInputStream("D://nio.txt").getChannel(),
			out=new FileOutputStream("D://nio1.txt").getChannel();
			ByteBuffer bb=ByteBuffer.allocate(1024);
			while((in.read(bb)!=-1)){
				//�ڽ�ByteBuffer�����е���Ϣд�뵽��ĵط���ʱ��  ��Ҫ����   ��clear����Ҫ����
				bb.flip();
				out.write(bb);
				//������ε�д������  ���뽫������������ɾ� ��Ȼ�Ļ��Ὣǰһ�ε�Ҳд��
				bb.clear();
			}
	}
	
	//�������Ի������͵�֧��
	@Test
	public void basic(){
		ByteBuffer bb=ByteBuffer.allocate(1024);
		int limit = bb.limit();
		System.out.println("limit:"+limit);
		//�������ݵ��ʼ����
		bb.rewind();
		bb.asCharBuffer().put("HELLO BASIC");
		char c;
		while((c=bb.getChar())!=0){
			System.out.print(c+" ");
		}
		
		bb.rewind();
		//���������ļ�������  Ҳ�����Ƶ��÷�
		bb.asDoubleBuffer().put(1000D);
		double d = bb.getDouble();
		System.out.println(d);
		
	}
	
	@Test
	public void detials(){
		ByteBuffer bb=ByteBuffer.allocate(1024);
		bb.asCharBuffer().put("detils".toCharArray());
		//��ǵ�ǰ��position
		bb.mark();
		char c1 = bb.getChar();
		char c2 = bb.getChar();
		System.out.println(c1+" "+c2);
		//���ص�markλ��  ע�͵Ļ�  �������ͻ᲻һ��
		bb.reset();
		char c3 = bb.getChar();
		char c4 = bb.getChar();
		/*���Ƿ�������Ľ����ǰ�����һ����  ������Ϊǰ�����Ǳ�ǵ�markΪ��ʼ��λ��(
		      ��Ϊ��ʱ���positionΪ��ʼ��λ�� .Ȼ�������ڵ���reset��ʱ��  �ͻص���mark��λ��)
		  ������ǽ�bb.reset()��δ����ע�͵��Ļ�  ��ô���ʱ�����ǿ����Ľ���ͻ᲻һ��
		*/
		System.out.println(c3+" "+c4);
		//���ػ������� ����    bb.limit(int lim);����limit��ֵ
		int l = bb.limit();
		System.out.println("limit:"+l);
		
		/*���ص�ǰ��λ��  ��Ϊʹ�õ���unicode����  �������ʱ��᷵��4(���������reset����  ���򷵻�8)
		* bb.position(pos)����position��ֵ
		*/int position = bb.position();
		System.out.println(position);
		
		/*
		 * ����limit-position
		 * 
		 * */
		int re = bb.remaining();
		System.out.println("limit-position:"+re);
		/*
		 *�Ƿ���position��limit֮�� 
		 */
		boolean hasElement = bb.hasRemaining();
		System.out.println(hasElement);
		
		//���ػ�����������
		int capacity = bb.capacity();
		System.out.println("capacity:"+capacity);
		//�������ʱ��position�����ʼ��λ��
		bb.rewind();
		
		//�ڽ������������ݽ��ж�ȡ��ȥ��ʱ��  ����  ���ѻ�����
		bb.flip();
		//�ڽ�Ҫ�����ݷ��뵽������֮ǰ   ��֮ǰ���ڻ�����������ȫ�����
		bb.clear();
	}
	/*
	 * ʹ���ļ�ӳ��ķ���  ���Ժܴ���ṩ����
	 * */
	@Test
	public void map(){
		int length=0x8FFFFFF;
		try {
			MappedByteBuffer mbb=new RandomAccessFile("D://map.test","rw").getChannel().
					map(MapMode.READ_WRITE, 0, length);
			
			for(int i=0;i<length;i++){
				mbb.put((byte)'x');
			}
			
			for(int i=0;i<length/2+6;i++){
				System.out.println((char)mbb.get(i));
			}
		} catch (Exception e) {
			
		}
		
	}
}
