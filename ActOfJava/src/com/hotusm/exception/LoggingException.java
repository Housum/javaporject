package com.hotusm.exception;

/**
 * 
 * @author Hotusm
 * 自定义异常
 */
public class LoggingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 328860245910971675L;
	
	 public LoggingException(String message) {
	        super(message);
	    }
	    public LoggingException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public LoggingException(Throwable cause) {
	        super(cause);
	    }

	    protected LoggingException(String message, Throwable cause,
	                        boolean enableSuppression,
	                        boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }
	    
	    /*
	     * throw:关键字-将异常抛出
	     * 
	     */
	    public static void main(String[] args) {
	    	try {
				throw new LoggingException("ss");
			} catch (LoggingException e) {
				e.printStackTrace();
			}
		}
	
	    
	    /*throws:异常说明,提供给客户端程序员友好的提示,在方法后面(这样在方法中不会抛出任何异常
	     * 除了运行时异常
	     * )
	     * 
	     * 
	     * */
	    public void testSimpleException() throws LoggingException{
	    	throw new LoggingException("test ");
	    }
	
}
