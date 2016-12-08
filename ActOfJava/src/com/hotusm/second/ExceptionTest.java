package com.hotusm.second;

/***
 * 
 * @author Hotusm
 * 异常处理测试类
 */
public class ExceptionTest {
	
	public static void main(String[] args) {
		try {
			throw new SimpleException("测试异常");
		} catch (SimpleException e) {
			e.printStackTrace();
			return;
		/*
		 * 无论是否有return,finally内的代码都会执行
		 * */
		}finally{
			/*
			 * 无论是否发生异常,finally代码块都会执行
			 * */
			System.out.println("测试finally");
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
