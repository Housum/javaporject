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

public class SocketServer2 {
	
	private static Object mutx=new Object();
	
	 static {
	        BasicConfigurator.configure();
	    }

	    private static final Log LOGGER = LogFactory.getLog(SocketServer2.class);

	    public static void main(String[] args) throws Exception{
	        ServerSocket serverSocket = new ServerSocket(83);

	        try {
	            while(true) {
	              //  Socket socket = serverSocket.accept();
	                //��Ȼҵ������̿��Խ���һ���̣߳��������ʹ���̳߳أ�,�����̵߳Ĵ����Ǻܺ���Դ�ġ�
	                //���ոı䲻��.accept()ֻ��һ��һ������socket�����,���ұ����������
	                //SocketServerThread socketServerThread = new SocketServerThread(socket);
	                //new Thread(socketServerThread).start();
	                
	                
	                //==============���Ƿ�����IO=================
	            	serverSocket.setSoTimeout(100);
	                Socket socket=null;
	                try {
	                	socket=serverSocket.accept();
					} catch (SocketTimeoutException  e) {
						synchronized (mutx) {
							SocketServer2.LOGGER.info("���û�дӵײ���յ��������ݱ��ģ��ȴ�10���룬ģ���¼�X�Ĵ���ʱ��");
	                        SocketServer2.mutx.wait(1000);
						}
					}
	            }
	        } catch(Exception e) {
	            SocketServer2.LOGGER.error(e.getMessage(), e);
	        } finally {
	            if(serverSocket != null) {
	                serverSocket.close();
	            }
	        }
	    }
}
class SocketServerThread implements Runnable {

    /**
     * ��־
     */
    private static final Log LOGGER = LogFactory.getLog(SocketServerThread.class);

    private Socket socket;

    public SocketServerThread (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            //����������ȡ��Ϣ
            in = socket.getInputStream();
            out = socket.getOutputStream();
            Integer sourcePort = socket.getPort();
            int maxLen = 1024;
            byte[] contextBytes = new byte[maxLen];
            //ʹ���̣߳�ͬ���޷����read�������������⣬
            //Ҳ����˵read������ͬ���ᱻ������ֱ������ϵͳ������׼����
            int realLen = in.read(contextBytes, 0, maxLen);
            //��ȡ��Ϣ
            String message = new String(contextBytes , 0 , realLen);

            //�����ӡ��Ϣ
            SocketServerThread.LOGGER.info("�������յ������ڶ˿ڣ�" + sourcePort + "����Ϣ��" + message);

            //���濪ʼ������Ϣ
            out.write("�ط���Ӧ��Ϣ��".getBytes());
        } catch(Exception e) {
            SocketServerThread.LOGGER.error(e.getMessage(), e);
        } finally {
            //��ͼ�ر�
            try {
                if(in != null) {
                    in.close();
                }
                if(out != null) {
                    out.close();
                }
                if(this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e) {
                SocketServerThread.LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
