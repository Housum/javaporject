package com.hotusm.first;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class SocketServer1 {

	static {
        BasicConfigurator.configure();
    }

    /**
     * ��־
     */
    private static final Log LOGGER = LogFactory.getLog(SocketServer1.class);

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(83);

        try {
            while(true) {
                Socket socket = serverSocket.accept();

                //����������ȡ��Ϣ
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                Integer sourcePort = socket.getPort();
                int maxLen = 2048;
                byte[] contextBytes = new byte[maxLen];
                //����Ҳ�ᱻ������ֱ��������׼����
                int realLen = in.read(contextBytes, 0, maxLen);
                //��ȡ��Ϣ
                String message = new String(contextBytes , 0 , realLen);

                //�����ӡ��Ϣ
                SocketServer1.LOGGER.info("�������յ������ڶ˿ڣ�" + sourcePort + "����Ϣ��" + message);

                //���濪ʼ������Ϣ
                out.write("�ط���Ӧ��Ϣ��".getBytes());

                //�ر�
                out.close();
                in.close();
                socket.close();
            }
        } catch(Exception e) {
            SocketServer1.LOGGER.error(e.getMessage(), e);
        } finally {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
