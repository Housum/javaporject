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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class SocketServer2 {

	static {
        BasicConfigurator.configure();
    }

    /**
     * ��־
     */
    private static final Log LOGGER = LogFactory.getLog(SocketServer2.class);

    /**
     * �Ľ���java nio server�Ĵ����У�����buffer�Ĵ�С���õıȽ�С��
     * ���ǲ��ٰ�һ��clientͨ��socket channel��δ�������������Ϣ������beff���ˣ���Ϊ�����治�£�<br>
     * ����ʹ��socketchanel��hashcode��Ϊkey����Ȼ��Ҳ�����Լ�ȷ��һ��id������Ϣ��stringbuffer��Ϊvalue���洢���������˵�һ���ڴ�����MESSAGEHASHCONTEXT��
     * 
     * ����������ConcurrentHashMap�����ú͹���ԭ�������аٶ�/Google
     */
    private static final ConcurrentMap<Integer, StringBuffer> MESSAGEHASHCONTEXT = new ConcurrentHashMap<Integer , StringBuffer>();


		//ͨ����					ͨ������		�ɹ�ע���¼�
		//ServerSocketChannel	��������ͨ��	SelectionKey.OP_ACCEPT
		//DatagramChannel		UDPЭ��ͨ��	SelectionKey.OP_READ��SelectionKey.OP_WRITE
		//SocketChannel			TCPЭ��ͨ��	SelectionKey.OP_READ��SelectionKey.OP_WRITE��SelectionKey.OP_CONNECT
    
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
                        SocketServer2.LOGGER.info("======channelͨ���Ѿ�׼����=======");
                        /*
                         * ��server socket channelͨ���Ѿ�׼���ã��Ϳ��Դ�server socket channel�л�ȡsocketchannel��
                         * �õ�socket channel��Ҫ��������������ϵ�selectorע�����socket channel����Ȥ�����顣
                         * �����޷����������socket channel���������
                         * */
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectableChannel;
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        registerSocketChannel(socketChannel , selector);

                    } else if(readyKey.isValid() && readyKey.isConnectable()) {
                        SocketServer2.LOGGER.info("======socket channel ��������=======");
                    } else if(readyKey.isValid() && readyKey.isReadable()) {
                        SocketServer2.LOGGER.info("======socket channel ����׼����ɣ�����ȥ��==��ȡ=======");
                        readSocketChannel(readyKey);
                    }
                }
            }
        } catch(Exception e) {
            SocketServer2.LOGGER.error(e.getMessage() , e);
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
        //���һ��������Ϊ Ϊ���socketchanne����Ļ�����
        socketChannel.register(selector, SelectionKey.OP_READ , ByteBuffer.allocate(50));
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
        //��Σ�Ϊ����ʾbuff��ʹ�÷�ʽ�����ǹ�����С��buff��������С��50byte��
        //�Ա���ʾchannel��buff�Ķ�ζ�д����
        int realLen = 0;
        StringBuffer message = new StringBuffer();
        //��仰����˼�ǣ���Ŀǰͨ���е�����д�뵽������
        //����д�������������buff������
        while((realLen = clientSocketChannel.read(contextBytes)) != 0) {

            //һ��Ҫ��buffer�л��ɡ�����ģʽ����������limit = capacity
            //��readû��д��������£��ͻᵼ�¶��
            contextBytes.flip();
            int position = contextBytes.position();
            int capacity = contextBytes.capacity();
            byte[] messageBytes = new byte[capacity];
            contextBytes.get(messageBytes, position, realLen);

            //���ַ�ʽҲ�ǿ��Զ�ȡ���ݵģ����Ҳ��ù���position��λ�á�
            //��Ϊ��ĿǰcontextBytes���е�����ȫ��ת��Ϊһ��byte���顣
            //ʹ�����ַ�ʽʱ��һ��Ҫ�Լ����ƺö�ȡ������λ�ã�realLen����Ҫ��
            //byte[] messageBytes = contextBytes.array();

            //ע��������������⣬�Ҹ���ϲ����ʹ��URLDecoder/URLEncoder�����н���롣
            //��Ȼjava nio��ܱ���Ҳ�ṩ����뷽ʽ�������˿�
            String messageEncode = new String(messageBytes , 0 , realLen , "UTF-8");
            message.append(messageEncode);

            //���л��ɡ�д��ģʽ��ֱ���������ķ�ʽ������
            contextBytes.clear();
        }

        //������ֱ��ν��յ���Ϣ����over�ؼ��֣�˵����Ϣ��������
        if(URLDecoder.decode(message.toString(), "UTF-8").indexOf("over") != -1) {
            //���messageHashContext�У�ȡ��֮ǰ�Ѿ��յ�����Ϣ����ϳ���������Ϣ
            Integer channelUUID = clientSocketChannel.hashCode();
            SocketServer2.LOGGER.info("�˿�:" + resoucePort + "�ͻ��˷�������Ϣ======message : " + message);
            StringBuffer completeMessage;
            //���MESSAGEHASHCONTEXT�е���ʷ��¼
            StringBuffer historyMessage = MESSAGEHASHCONTEXT.remove(channelUUID);
            if(historyMessage == null) {
                completeMessage = message;
            } else {
                completeMessage = historyMessage.append(message);
            }
            SocketServer2.LOGGER.info("�˿�:" + resoucePort + "�ͻ��˷�����������Ϣ======completeMessage : " + URLDecoder.decode(completeMessage.toString(), "UTF-8"));

            //======================================================
            //          ��Ȼ������ɺ󣬿�����������ʽ����ҵ����        
            //======================================================

            //�ط����ݣ����ر�channel
            ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("�ط�������", "UTF-8").getBytes());
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        } else {
            //���û�з����С�over���ؼ��֣�˵����û�н����꣬�򽫱��ν��ܵ�����Ϣ����messageHashContext
            SocketServer2.LOGGER.info("�˿�:" + resoucePort + "�ͻ�����Ϣ��δ�����꣬��������======message : " + URLDecoder.decode(message.toString(), "UTF-8"));
            //ÿһ��channel�����Ƕ����ģ����Կ���ʹ�ö����hashֵ����ΪΨһ��ʾ
            Integer channelUUID = clientSocketChannel.hashCode();

            //Ȼ���ȡ���channel����ǰ�Ѿ��ﵽ��message��Ϣ
            StringBuffer historyMessage = MESSAGEHASHCONTEXT.get(channelUUID);
            if(historyMessage == null) {
                historyMessage = new StringBuffer();
                MESSAGEHASHCONTEXT.put(channelUUID, historyMessage.append(message));
            }
        }
    }
}
