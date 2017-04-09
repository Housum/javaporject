package com.hotusm.internet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEndport {
	
	public static void main(String[] args) {
		try {
			ServerSocket ss=new ServerSocket(7788);
			while(true){
				Socket client = ss.accept();
				doClient(client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void doClient(final Socket client){
		new Thread(){
			public void run() {
				new Thread(new Reader(client)).start();
			};
		}.start();
	}
	
	static class Reader implements Runnable{

		private Socket input;
		
		public Reader(Socket input) {
			this.input=input;
		}
		@Override
		public void run() {
			InputStream inputStream=null;
			InputStreamReader reader=null;
			while(!input.isInputShutdown()){
				try {
//					inputStream = input.getInputStream();
//					reader=new InputStreamReader(inputStream);
//					for(int c=reader.read();c!=-1;c=reader.read()){
//						System.out.print((char)c);
//					}
					OutputStream outputStream = input.getOutputStream();
					String content="<html>"
							+ "<body>"
							+"<h1>111111111</h1>"
							+"<body>"
							+ "</html>";
					
					String header=   "HTTP/1.1 200 OK\r\n"
									+"Server: JTTP2.0\r\n"
									+"Content-type: text/html; charset=utf-8\r\n"
									+"Date:Tue, 13 Sep 2016 13:43:59 GMT"
									+"Content-length:"+content.length()+"\r\n\r\n";
				//	File f=new File("C:/test.png");
				//	InputStream in=new FileInputStream(f);
//					for(int c=in.read();c!=-1;c=in.read()){
//						outputStream.write(c);
//					}
					outputStream.write(header.getBytes());
					outputStream.write("<img src='http://hotusm.oss-cn-beijing.aliyuncs.com/userfile/03fb5598-f4d5-46ec-b772-23aeae5e646e.png'/>".getBytes());
					outputStream.flush();
					input.close();
				} catch (IOException e) {
					break;
				}
			}
		}
	}
	static class Writer implements Runnable{

		private OutputStream output;
		
		public Writer(OutputStream output) {
			this.output=output;
			
		}
		@Override
		public void run() {
			while(true){
				
			}
		}
	}
}
