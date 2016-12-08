package com.hotusm.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * URI 统一资源定位符
 * URL 不仅是简单的指向一个资源 还能够提供一个特定的网络地址
 * 并且可以从网路上获取数据
 */
public class URITest {
	
	@Test
	public void url() throws IOException{
		URL url=new URL("http://www.baidu.com");
		
		try {
			InputStream in = url.openStream();
			byte[] b=new byte[1024];
			while(in.read(b)!=-1){
				System.out.println(new String(b,"UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int port = url.getPort();
		System.out.println(port);
		
		Object obj = url.getContent();
		System.out.println(obj.getClass());
		
		
	}
	
	@Test
	public void openConnection() throws IOException{
		URL url=new URL("http://www.baidu.com");
		URLConnection urlConnection = url.openConnection();
		Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
		for(Map.Entry<String, List<String>> entry:headerFields.entrySet()){
			System.out.println(entry.getKey()+" : "+entry.getValue());
		}
	}
	
	@Test
	public void encoderAndDecoder(){
		try {
			System.out.println(URLEncoder.encode("http://www.baidu.com/一二三","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
