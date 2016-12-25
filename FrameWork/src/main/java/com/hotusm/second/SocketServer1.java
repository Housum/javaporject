package com.hotusm.second;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

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

	    public static void main(String[] args) throws Exception {
	        ServerSocketChannel serverChannel = ServerSocketChannel.open();
	        serverChannel.configureBlocking(false);
	        ServerSocket serverSocket = serverChannel.socket();
	        serverSocket.setReuseAddress(true);
	        serverSocket.bind(new InetSocketAddress(83));

	        Selector selector = Selector.open();
	        //ע�⡢������ͨ��ֻ��ע��SelectionKey.OP_ACCEPT�¼�
	        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

	        try {
	            while(true) {
	                //�������������˵������ѯ��selector����û�л�ȡ���κ�׼���õġ�����Ȥ���¼�
	                //java����Զ�·����IO��֧��Ҳ����������ģʽ �ͷ�����ģʽ���֡�
	                if(selector.select(100) == 0) {
	                    //================================================
	                    //      ������ҵ�������������һЩȻ���ѵ�����
	                    //================================================
	                    continue;
	                }
	                //������Ǳ���ѯ�ʲ���ϵͳ������ȡ���ġ������ĵ��¼������¼����ͣ�ÿһ��ͨ�����Ƕ����ģ�
	                Iterator<SelectionKey> selecionKeys = selector.selectedKeys().iterator();

	                while(selecionKeys.hasNext()) {
	                    SelectionKey readyKey = selecionKeys.next();
	                    //����Ѿ������readyKeyһ��Ҫ�Ƴ���������Ƴ����ͻ�һֱ������selector.selectedKeys������
	                    //������һ��selector.select() > 0ʱ�����readyKey�ֻᱻ����һ��
	                    selecionKeys.remove();

	                    SelectableChannel selectableChannel = readyKey.channel();
	                    if(readyKey.isValid() && readyKey.isAcceptable()) {
	                        SocketServer1.LOGGER.info("======channelͨ���Ѿ�׼����=======");
	                        /*
	                         * ��server socket channelͨ���Ѿ�׼���ã��Ϳ��Դ�server socket channel�л�ȡsocketchannel��
	                         * �õ�socket channel��Ҫ��������������ϵ�selectorע�����socket channel����Ȥ�����顣
	                         * �����޷����������socket channel���������
	                         * */
	                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectableChannel;
	                        SocketChannel socketChannel = serverSocketChannel.accept();
	                        registerSocketChannel(socketChannel , selector);

	                    } else if(readyKey.isValid() && readyKey.isConnectable()) {
	                        SocketServer1.LOGGER.info("======socket channel ��������=======");
	                    } else if(readyKey.isValid() && readyKey.isReadable()) {
	                        SocketServer1.LOGGER.info("======socket channel ����׼����ɣ�����ȥ��==��ȡ=======");
	                        readSocketChannel(readyKey);
	                    }
	                }
	            }
	        } catch(Exception e) {
	            SocketServer1.LOGGER.error(e.getMessage() , e);
	        } finally {
	            serverSocket.close();
	        }
	    }
	    
	    /**
	     * ��server socket channel���յ�/׼���� һ���µ� TCP���Ӻ�
	     * �ͻ�����򷵻�һ���µ�socketChannel��<br>
	     * ��������µ�socket channel��û����selector��ѡ����/����������ע�ᣬ
	     * ���Գ���û��ͨ��selector֪ͨ���socket channel���¼���
	     * ���������õ��µ�socket channel��Ҫ���ĵ�һ��������ǵ�selector��ѡ����/����������ע�����
	     * socket channel����Ȥ���¼�
	     * @param socketChannel �µ�socket channel
	     * @param selector selector��ѡ����/��������
	     * @throws Exception
	     */
	    private static void registerSocketChannel(SocketChannel socketChannel , Selector selector) throws Exception {
	        socketChannel.configureBlocking(false);
	        //socketͨ��������ֻ����ע�������¼�SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT
	        socketChannel.register(selector, SelectionKey.OP_READ , ByteBuffer.allocate(2048));
	    }

	    /**
	     * ����������ڶ�ȡ�ӿͻ��˴�������Ϣ��
	     * ���ҹ۲�ӿͻ��˹�����socket channel�ھ�����δ�����Ƿ���ɴ��䡣
	     * ���������ɣ��򷵻�һ��true�ı�ǡ�
	     * @param socketChannel
	     * @throws Exception
	     */
	    private static void readSocketChannel(SelectionKey readyKey) throws Exception {
	        SocketChannel clientSocketChannel = (SocketChannel)readyKey.channel();
	        //��ȡ�ͻ���ʹ�õĶ˿�
	        InetSocketAddress sourceSocketAddress = (InetSocketAddress)clientSocketChannel.getRemoteAddress();
	        Integer resoucePort = sourceSocketAddress.getPort();

	        //�õ����socket channelʹ�õĻ�������׼����ȡ����
	        //�ں��ģ�����ϸ���⻺�������÷����ʵ������Ҫ�ľ�������Ԫ��capacity,position��limit��
	        ByteBuffer contextBytes = (ByteBuffer)readyKey.attachment();
	        //��ͨ��������д�뵽��������ע����д�뵽��������
	        //����֮ǰ������ByteBuffer�Ĵ�СΪ2048 byte�����Կ��Դ���д�벻������
	        //û��ϵ�����Ǻ������������롣����������ʱ���Ϊһ�ν��ܿ������
	        int realLen = -1;
	        try {
	            realLen = clientSocketChannel.read(contextBytes);
	        } catch(Exception e) {
	            //�����׳����쳣��һ����ǿͻ�����Ϊĳ��ԭ����ֹ�ˡ����Թر�channel������
	            SocketServer1.LOGGER.error(e.getMessage());
	            clientSocketChannel.close();
	            return;
	        }

	        //�����������û���κ����ݣ���ʵ���������̫���ܣ�����Ͳ��ᴥ��OP_READ�¼��ˣ�
	        if(realLen == -1) {
	            SocketServer1.LOGGER.warn("====������û�����ݣ�====");
	            return;
	        }

	        //����������д״̬�л�Ϊ��״̬��ʵ������������Ƕ�дģʽ���л�����
	        //����java nio����е����socket channel��д����ȫ���ȴ���
	        contextBytes.flip();
	        //ע��������������⣬�Ҹ���ϲ����ʹ��URLDecoder/URLEncoder�����н���롣
	        //��Ȼjava nio��ܱ���Ҳ�ṩ����뷽ʽ�������˿�
	        byte[] messageBytes = contextBytes.array();
	        String messageEncode = new String(messageBytes , "UTF-8");
	        String message = URLDecoder.decode(messageEncode, "UTF-8");

	        //����յ��ˡ�over���ؼ��֣��Ż����buffer�����ط����ݣ�
	        //������ջ��棬��Ҫ��ԭbuffer�ġ�д״̬��
	        if(message.indexOf("over") != -1) {
	            //����Ѿ���ȡ�Ļ��棬�������л�Ϊд״̬(����Ҫע��clear()��capacity()��������������)
	            contextBytes.clear();
	            SocketServer1.LOGGER.info("�˿�:" + resoucePort + "�ͻ��˷�������Ϣ======message : " + message);

	            //======================================================
	            //          ��Ȼ������ɺ󣬿�����������ʽ����ҵ����        
	            //======================================================

	            //�ط����ݣ����ر�channel
	            ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("�ط�������", "UTF-8").getBytes());
	            clientSocketChannel.write(sendBuffer);
	            clientSocketChannel.close();
	        } else {
	            SocketServer1.LOGGER.info("�˿�:" + resoucePort + "�ͻ�����Ϣ��δ�����꣬��������======message : " + message);
	            //���ǣ�limit��capacity��ֵһ�£�position��λ����realLen��λ��
	            contextBytes.position(realLen);
	            contextBytes.limit(contextBytes.capacity());
	        }
	    }
}
