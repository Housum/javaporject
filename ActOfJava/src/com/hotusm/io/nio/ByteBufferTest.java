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
 *通过FileChannel和ByteBuffer进行对NIO进行操作
 *NIO的性能是最高的
 *
 */
public class ByteBufferTest {
	
	public static String FILENAME="D://nio.txt";

	/**
	 * 将数据写入到文件中  在其中涉及到操作  都是通过FileChannel(管道) ByteBuffer(缓冲区)
	 * 进行操作的
	 */
	@Test
	public void bytebuffer(){
		FileOutputStream os=null;
		try {
			os=new FileOutputStream(FILENAME);
			FileChannel channel = os.getChannel();
			channel.write(ByteBuffer.wrap("你好啊  NIO".getBytes()));
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
			//准备缓存区  以便信息可以由write()提取
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
				//在将ByteBuffer缓存中的信息写入到别的地方的时候  需要调用   和clear都需要调用
				bb.flip();
				out.write(bb);
				//遇到多次的写入的情况  必须将缓存区的清理干净 不然的话会将前一次的也写入
				bb.clear();
			}
	}
	
	//缓存区对基本类型的支持
	@Test
	public void basic(){
		ByteBuffer bb=ByteBuffer.allocate(1024);
		int limit = bb.limit();
		System.out.println("limit:"+limit);
		//跳到数据的最开始部分
		bb.rewind();
		bb.asCharBuffer().put("HELLO BASIC");
		char c;
		while((c=bb.getChar())!=0){
			System.out.print(c+" ");
		}
		
		bb.rewind();
		//对于其他的几种类型  也是相似的用法
		bb.asDoubleBuffer().put(1000D);
		double d = bb.getDouble();
		System.out.println(d);
		
	}
	
	@Test
	public void detials(){
		ByteBuffer bb=ByteBuffer.allocate(1024);
		bb.asCharBuffer().put("detils".toCharArray());
		//标记当前的position
		bb.mark();
		char c1 = bb.getChar();
		char c2 = bb.getChar();
		System.out.println(c1+" "+c2);
		//返回到mark位置  注释的话  输入结果就会不一样
		bb.reset();
		char c3 = bb.getChar();
		char c4 = bb.getChar();
		/*我们发现输出的结果和前面的是一样的  这是因为前面我们标记的mark为开始的位置(
		      因为那时候的position为开始的位置 .然后我们在调用reset的时候  就回到了mark的位置)
		  如果我们将bb.reset()这段代码给注释掉的话  那么这个时候我们看到的结果就会不一样
		*/
		System.out.println(c3+" "+c4);
		//返回缓存区的 限制    bb.limit(int lim);设置limit的值
		int l = bb.limit();
		System.out.println("limit:"+l);
		
		/*返回当前的位置  因为使用的是unicode编码  所以这个时候会返回4(这里调用了reset方法  否则返回8)
		* bb.position(pos)设置position的值
		*/int position = bb.position();
		System.out.println(position);
		
		/*
		 * 返回limit-position
		 * 
		 * */
		int re = bb.remaining();
		System.out.println("limit-position:"+re);
		/*
		 *是否在position和limit之间 
		 */
		boolean hasElement = bb.hasRemaining();
		System.out.println(hasElement);
		
		//返回缓存区的容量
		int capacity = bb.capacity();
		System.out.println("capacity:"+capacity);
		//这个操作时将position跳到最开始的位置
		bb.rewind();
		
		//在将缓存区的数据进行读取出去的时候  调用  提醒缓存区
		bb.flip();
		//在将要把数据放入到缓存区之前   将之前存在缓存区的数据全部清空
		bb.clear();
	}
	/*
	 * 使用文件映射的方法  可以很大的提供性能
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
