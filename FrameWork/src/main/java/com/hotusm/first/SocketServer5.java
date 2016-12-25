package com.hotusm.first;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class SocketServer5 {
	
	static {
        BasicConfigurator.configure();
    }

    private static Object xWait = new Object();

    private static final Log LOGGER = LogFactory.getLog(SocketServer5.class);

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(83);
        serverSocket.setSoTimeout(100);
        try {
            while(true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch(SocketTimeoutException e1) {
                    //===========================================================
                    //      ִ�е����˵������acceptû�н��յ��κ�TCP����
                    //      ���߳�������Ϳ�����һЩ���飬��ΪX
                    //===========================================================
                    synchronized (SocketServer5.xWait) {
                        SocketServer5.LOGGER.info("���û�дӵײ���յ��κ�TCP���ӣ��ȴ�10���룬ģ���¼�X�Ĵ���ʱ��");
                        SocketServer5.xWait.wait(10);
                    }
                    continue;
                }
                //��Ȼҵ������̿��Խ���һ���̣߳��������ʹ���̳߳أ�,�����̵߳Ĵ����Ǻܺ���Դ�ġ�
                //���ոı䲻��.accept()ֻ��һ��һ������socket���ӵ����
                SocketServerThread1 SocketServerThread1 = new SocketServerThread1(socket);
                new Thread(SocketServerThread1).start();
            }
        } catch(Exception e) {
            SocketServer5.LOGGER.error(e.getMessage(), e);
        } finally {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
/**
 * ��Ȼ�����յ��ͻ��˵�socket��ҵ��Ĵ�����̿��Խ���һ���߳�������
 * �����Ǹı䲻��socket��һ��һ������accept()�������
 * @author yinwenjie
 */
class SocketServerThread1 implements Runnable {

    /**
     * ��־
     */
    private static final Log LOGGER = LogFactory.getLog(SocketServerThread1.class);

    private Socket socket;

    public SocketServerThread1 (Socket socket) {
        this.socket = socket;
    }

    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            Integer sourcePort = socket.getPort();
            int maxLen = 2048;
            byte[] contextBytes = new byte[maxLen];
            int realLen;
            StringBuffer message = new StringBuffer();
            //����������ȡ��Ϣ�����óɷ�������ʽ������read��Ϣ��ʱ���ֿ�����һЩ�������飩
            this.socket.setSoTimeout(10);
            //��������   ���break �ܹ�����Ŀ���
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
                    SocketServerThread1.LOGGER.info("���û�дӵײ���յ��������ݱ��ģ��ȴ�10���룬ģ���¼�Y�Ĵ���ʱ��");
                    continue;
                }
            }
            //�����ӡ��Ϣ
            Long threadId = Thread.currentThread().getId();
            SocketServerThread1.LOGGER.info("������(�̣߳�" + threadId + ")�յ������ڶ˿ڣ�" + sourcePort + "����Ϣ��" + message);

            //���濪ʼ������Ϣ
            out.write("�ط���Ӧ��Ϣ��".getBytes());

            //�ر�
            out.close();
            in.close();
            this.socket.close();
        } catch(Exception e) {
            SocketServerThread1.LOGGER.error(e.getMessage(), e);
        }
    }
}
