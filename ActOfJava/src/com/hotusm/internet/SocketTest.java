package com.hotusm.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class SocketTest {
	
	@Test
	public void connect() throws UnknownHostException, IOException{
		try (Socket socket=new Socket("time.nist.gov",13)){
			
			//设置超时时间
			socket.setSoTimeout(15000);
			InputStream inputStream = socket.getInputStream();
			InputStreamReader reader=new InputStreamReader(inputStream, "UTF-8");
			StringBuffer sb=new StringBuffer();
			for(int c=reader.read();c!=-1;c=reader.read()){
				sb.append((char)c);
			}
			
			try {
				parseDate(sb.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} finally {
			
		}
	}
	
	void parseDate(String date) throws ParseException{

		String[] chars = date.split(" ");
		String dd=chars[1]+" "+chars[2];
		System.out.println(dd);
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parse = dateFormat.parse(dd);
		System.out.println(parse);
	}
	
	@Test
	public void testPort(){
		for(int i=0;i<1024;i++){
			
			try {
				Socket conn=new Socket("localhost", i);
				System.out.println("port :"+i+" is used");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	@Test
	public void param(){
		try {
			Socket conn=new Socket("www.baidu.com", 80);
			//在我们连接的时候  客户端会使用一个随机没有使用的端口
			int port=conn.getLocalPort();
			System.out.println("port:"+port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
