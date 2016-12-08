package com.hotusm.designModel.decorator;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * 
 * @author Hotusm  <br/>
 * @date 2016��10��8��   <br/>
 * @description  java.io�д������඼ʹ����װ����ģʽ  
 * ���е�װ���߶��̳���FilterInputStream���������   
 * �������������Զ����IO����  ȫ����ΪСд
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
