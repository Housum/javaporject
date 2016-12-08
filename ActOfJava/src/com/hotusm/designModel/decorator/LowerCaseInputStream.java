package com.hotusm.designModel.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016年10月8日   <br/>
 * @description  java.io中大量的类都使用了装饰者模式  
 * 所有的装饰者都继承了FilterInputStream这个抽闲类   
 * 下面这个类就是自定义的IO处理  全部变为小写
 * 
 */
public class LowerCaseInputStream extends FilterInputStream{

	protected LowerCaseInputStream(InputStream in) {
		super(in);
	}
	
	@Override
	public int read() throws IOException {
		
		int c=super.read();
		
		return (c==-1?c:Character.toLowerCase((char)c));
	}
	
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		
		int result=super.read(b, off, len);
		
		for(int i=off;i<off+result;i++){
			
			b[i]=(byte)Character.toLowerCase((char)b[i]);
		}
		
		return result;
	}
	
	
}
