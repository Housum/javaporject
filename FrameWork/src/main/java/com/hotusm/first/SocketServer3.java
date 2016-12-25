package com.hotusm.first;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class SocketServer3 {

	 static {
	        BasicConfigurator.configure();
	    }

	    private static Object xWait = new Object();

	    /**
	     * ��־
	     */
	    private static final Log LOGGER = LogFactory.getLog(SocketServer3.class);

	    public static void main(String[] args) throws IOException {
	        ServerSocket serverSocket = null;

	        try {
	            serverSocket = new ServerSocket(83);
	            serverSocket.setSoTimeout(100);
	            while(true) {
	                Socket socket = null;
	                try {
	                    socket = serverSocket.accept();
	                } catch(SocketTimeoutException e1) {
	                    //===========================================================
	                    //      ִ�е����˵������acceptû�н��յ��κ�TCP����
	                    //      ���߳�������Ϳ�����һЩ���飬��ΪX
	                    //===========================================================
	                    synchronized (SocketServer3.xWait) {
	                        SocketServer3.LOGGER.info("���û�дӵײ���յ��κ�TCP���ӣ��ȴ�10���룬ģ���¼�X�Ĵ���ʱ��");
	                        SocketServer3.xWait.wait(10);
	                    }
	                    continue;
	                }

	                InputStream in = socket.getInputStream();
	                OutputStream out = socket.getOutputStream();
	                Integer sourcePort = socket.getPort();
	                int maxLen = 2048;
	                byte[] contextBytes = new byte[maxLen];
	                int realLen;
	                StringBuffer message = new StringBuffer();
	                //����������ȡ��Ϣ�����óɷ�������ʽ������read��Ϣ��ʱ���ֿ�����һЩ�������飩
	                socket.setSoTimeout(10);
	                BIORead:while(true) {
	                    try {
	                        while((realLen = in.read(contextBytes, 0, maxLen)) != -1) {
	                            message.append(new String(contextBytes , 0 , realLen));
	                            /*
	                             * ���Ǽ����ȡ����over���ؼ��֣�
	                             * ��ʾ�ͻ��˵�������Ϣ�ھ������ɴδ��ͺ����
	                             * */
	                            if(message.indexOf("over") != -1) {
	                                break BIORead;
	                            }
	                        }
	                    } catch(SocketTimeoutException e2) {
	                        //===========================================================
	                        //      ִ�е����˵������readû�н��յ��κ�������
	                        //      ���߳��������ֿ�����һЩ���飬��ΪY
	                        //===========================================================
	                        SocketServer3.LOGGER.info("���û�дӵײ���յ��������ݱ��ģ��ȴ�10���룬ģ���¼�Y�Ĵ���ʱ��");
	                        continue;
	                    }
	                }
	                //�����ӡ��Ϣ
	                SocketServer3.LOGGER.info("�������յ������ڶ˿ڣ�" + sourcePort + "����Ϣ��" + message);

	                //���濪ʼ������Ϣ
	                out.write("�ط���Ӧ��Ϣ��".getBytes());

	                //�ر�
	                out.close();
	                in.close();
	                socket.close();
	            } 
	        } catch(Exception e) {
	            SocketServer3.LOGGER.error(e.getMessage(), e);
	        } finally {
	            if(serverSocket != null) {
	                serverSocket.close();
	            }
	        }
	    }
}
