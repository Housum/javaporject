package com.hotusm.second;

/***
 * 
 * @author Hotusm
 * �쳣���������
 */
public class ExceptionTest {
	
	public static void main(String[] args) {
		try {
			throw new SimpleException("�����쳣");
		} catch (SimpleException e) {
			e.printStackTrace();
			return;
		/*
		 * �����Ƿ���return,finally�ڵĴ��붼��ִ��
		 * */
		}finally{
			/*
			 * �����Ƿ����쳣,finally����鶼��ִ��
			 * */
			System.out.println("����finally");
		}
	}
}
class SimpleException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}
	public SimpleException() {
		super();
	}
	public SimpleException(String message){
		super(message);
	}
	
}
