package com.hotusm.client;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HttpClientTest {
	
	public static void main(String[] args) throws InterruptedException {
//		HttpClient hc=new HttpClient();
//		GetMethod pm=new GetMethod("http://www.nmg-greencoal.com/Market%20Watch/");
//		int code=-1;
//		try {
//			do{
//				code = hc.executeMethod(pm);
//				if(code!=200){
//					System.out.println(code);
//					code=-1;
//				}else{
//					byte[] body = pm.getResponseBody();
//					String html=new String(body,"UTF-8");
//					//System.out.println(html);
//					Document doc = Jsoup.parse(html);
//					Elements ems = doc.select(".f14_b_2 a");
//					for(Element e:ems){
//						String href = e.attr("href");
//						String title = e.html();
//						System.out.println("href: http://www.nmg-greencoal.com/"+href+" title:"+title);
//					}
//				}
//			}while(code==1);
//			
//		} catch (HttpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String content = new HttpClientTest().content();
		System.out.println(content);
		
	}
	
	public String content(){
		HttpClient hc=new HttpClient();
		GetMethod pm=new GetMethod("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=111&rsv_pq=9e3d3e72000185c1&rsv_t=4e58ccj%2FyZKL3VZr4xSVTAm2MIeMs9B8BlWh7wRGEHj5wq5rUPFMItD3dug&rqlang=cn&rsv_enter=1&rsv_sug3=3&rsv_sug2=0&inputT=705&rsv_sug4=706");
		int code=-1;
		try {
			do{
				code = hc.executeMethod(pm);
				if(code!=200){
					System.out.println(code);
					code=-1;
				}else{
					byte[] body = pm.getResponseBody();
					String html=new String(body,"UTF-8");
					//System.out.println(html);
					Document doc = Jsoup.parse(html);
					Elements ems = doc.getElementById("digest").siblingElements();
					//content
					Elements e = ems.eq(2);
					//time
					return e.html();
				}
			}while(code==1);
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
