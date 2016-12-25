package com.hotusm.first;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class SocketClientRequestThread implements Runnable{

	 static {
	        BasicConfigurator.configure();
	    }

	    /**
	     * ��־
	     */
	    private static final Log LOGGER = LogFactory.getLog(SocketClientRequestThread.class);

	    private CountDownLatch countDownLatch;

	    /**
	     * ����߲�ı��
	     * @param countDownLatch
	     */
	    private Integer clientIndex;

	    /**
	     * countDownLatch��java�ṩ��ͬ����������
	     * ����������ֵ��Ϊ0ʱ����������Ӱ����ȴ����߳̽��ᱻ���������֤ģ�Ⲣ���������ʵ��
	     * @param countDownLatch
	     */
	    public SocketClientRequestThread(CountDownLatch countDownLatch , Integer clientIndex) {
	        this.countDownLatch = countDownLatch;
	        this.clientIndex = clientIndex;
	    }

	    public void run() {
	        Socket socket = null;
	        OutputStream clientRequest = null;
	        InputStream clientResponse = null;

	        try {
	            socket = new Socket("localhost",83);
	            clientRequest = socket.getOutputStream();
	            clientResponse = socket.getInputStream();

	            //�ȴ���ֱ��SocketClientDaemon��������̵߳�������Ȼ�������߳�һ��������
	            this.countDownLatch.await();

	            //����������Ϣ
	            clientRequest.write(("���ǵ�" + this.clientIndex + " ���ͻ��˵�����").getBytes());
	            clientRequest.flush();

	            //������ȴ���ֱ��������������Ϣ
	            SocketClientRequestThread.LOGGER.info("��" + this.clientIndex + "���ͻ��˵���������ɣ��ȴ�������������Ϣ");
	            int maxLen = 1024;
	            byte[] contextBytes = new byte[maxLen];
	            int realLen;
	            String message = "";
	            //����ִ�е������һֱ�ȴ�������������Ϣ��ע�⣬ǰ����in��out������close�����close�˾��ղ����������ķ����ˣ�
	            while((realLen = clientResponse.read(contextBytes, 0, maxLen)) != -1) {
	                message += new String(contextBytes , 0 , realLen);
	            }
	            SocketClientRequestThread.LOGGER.info("���յ����Է���������Ϣ:" + message);
	        } catch (Exception e) {
	            SocketClientRequestThread.LOGGER.error(e.getMessage(), e);
	        } finally {
	            try {
	                if(clientRequest != null) {
	                    clientRequest.close();
	                }
	                if(clientResponse != null) {
	                    clientResponse.close();
	                }
	            } catch (IOException e) {
	                SocketClientRequestThread.LOGGER.error(e.getMessage(), e);
	            }
	        }
	    }
}
