package com.hotusm.internet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientEndport {
	
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("localhost", 7788);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write("123".getBytes());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
