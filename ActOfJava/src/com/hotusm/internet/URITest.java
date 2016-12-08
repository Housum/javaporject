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
 * URI ͳһ��Դ��λ��
 * URL �����Ǽ򵥵�ָ��һ����Դ ���ܹ��ṩһ���ض��������ַ
 * ���ҿ��Դ���·�ϻ�ȡ����
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
			System.out.println(URLEncoder.encode("http://www.baidu.com/һ����","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
