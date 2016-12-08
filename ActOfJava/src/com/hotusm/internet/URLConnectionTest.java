package com.hotusm.internet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.junit.Test;

public class URLConnectionTest {
	
	/**
	 * URLConnection����Ĺ��캯����protected��  ����
	 * ������Դ����ֱ�Ӵ�������  һ���������ҪURL.openConnection()
	 * ���
	 * @throws IOException 
	 */
	@Test
	public void demo() throws IOException{
		URL url=new URL("http://www.baidu.com/");
		URLConnection conn = url.openConnection();
		
		URL url1 = conn.getURL();
		url1=new URL("http://mobiledev.yonyou.com");
		URL url2 = conn.getURL();
		System.out.println(url2);
		//MIME����
		String contentType = conn.getContentType();
		System.out.println("contentType:"+contentType);
		
		//ContentLength 
		int contentLength = conn.getContentLength();
		System.out.println("contentLength:"+contentLength);
		
		//lastModified
		long lastModified = conn.getLastModified();
		System.out.println("lastModified:"+new Date(lastModified));
		
		//expiration
		long expiration = conn.getExpiration();
		System.out.println("expiration:"+expiration);
		
		//getHeaderField��ȡ������ͷ
		String date = conn.getHeaderField("date");
		System.out.println("date:"+date);
		
	}
	
	@Test
	public void demo1() throws IOException{
		URL url=new URL("http://www.baidu.com/");
		URLConnection conn = url.openConnection();
		for(int i=0;;i++){
			String header = conn.getHeaderField(i);
			if(header==null)break;
			System.out.println(conn.getHeaderFieldKey(i)+" : "+header);
		}
	}
	
	public void demo2(){
		ResponseCache.getDefault();
	}
}
