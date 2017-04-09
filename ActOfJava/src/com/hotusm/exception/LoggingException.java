package com.hotusm.exception;

/**
 * 
 * @author Hotusm
 * �Զ����쳣
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
	     * throw:�ؼ���-���쳣�׳�
	     * 
	     */
	    public static void main(String[] args) {
	    	try {
				throw new LoggingException("ss");
			} catch (LoggingException e) {
				e.printStackTrace();
			}
		}
	
	    
	    /*throws:�쳣˵��,�ṩ���ͻ��˳���Ա�Ѻõ���ʾ,�ڷ�������(�����ڷ����в����׳��κ��쳣
	     * ��������ʱ�쳣
	     * )
	     * 
	     * 
	     * */
	    public void testSimpleException() throws LoggingException{
	    	throw new LoggingException("test ");
	    }
	
}
