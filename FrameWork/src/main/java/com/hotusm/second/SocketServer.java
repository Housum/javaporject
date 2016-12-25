package com.hotusm.second;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class SocketServer {

	static {
        BasicConfigurator.configure();
    }

    private static final Object waitObject = new Object();

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        /*
         * ����ʹ�õ��̳߳ؼ�������һ��Ҫ��˵����
         * 1��Executors���̳߳����ɹ��ߣ�ͨ������������ǿ��Ժ����ɵ����ɡ��̶���С���̳߳ء��������ȳء������������߳������ĳء��������뿴API Doc
         * 2����Ȼ��Ҳ����ͨ��ThreadPoolExecutorֱ�����ɳء�
         * 3������̳߳��������õ�����ϵͳ�ġ�IO�¼�֪ͨ���ģ������������С��õ�IO���ݺ��ҵ����ġ���Ҫ���к��ߵĲ�������������ʹ��һ���أ���ò�Ҫ���ã�
         * 4����Ҳ���Բ�ʹ���̳߳أ����Ƽ��������������ʹ���̳߳أ�ֱ��AsynchronousServerSocketChannel.open()�����ˡ�
         * */
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(threadPool);
        final AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open(group);

        //����Ҫ�����Ķ˿ڡ�0.0.0.0������������IP�豸
        serverSocket.bind(new InetSocketAddress("0.0.0.0", 83));
        //ΪAsynchronousServerSocketChannelע�������ע��ֻ��ΪAsynchronousServerSocketChannelͨ��ע�����
        //��������Ϊ ���ͻ��˺ͷ����� socketchannelͨ��ע��ļ���
        serverSocket.accept(null, new ServerSocketChannelHandle(serverSocket));

        //�ȴ����Ա�۲����������Ҫ�����ԭ����û���κι�ϵ��ֻ��Ϊ�˱�֤�ػ��̲߳����˳���
        synchronized(waitObject) {
            waitObject.wait();
        }
    }
}

/**
 * ����������࣬ר��������Ӧ ServerSocketChannel ���¼���
 * ���ǵ������ڡ��ܹ���ƣ�ϵͳ��ͨ�ţ�4������IOͨ��ģ�ͺ�JAVAʵ�� ��ƪ�������ᵽ��������ServerSocketChannelֻ��һ���¼������ܿͻ��˵�����
 * @author yinwenjie
 */
class ServerSocketChannelHandle implements CompletionHandler<AsynchronousSocketChannel, Void> {
    /**
     * ��־
     */
    private static final Log LOGGER = LogFactory.getLog(ServerSocketChannelHandle.class);

    private AsynchronousServerSocketChannel serverSocketChannel;

    /**
     * @param serverSocketChannel
     */
    public ServerSocketChannelHandle(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    /**
     * ע�⣬���Ƿֱ�۲� this��socketChannel��attachment���������id��
     * ���۲첻ͬ�ͻ������ӵ���ʱ������������ı仯����˵��ServerSocketChannelHandle�ļ���ģʽ
     */
    public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
        ServerSocketChannelHandle.LOGGER.info("completed(AsynchronousSocketChannel result, ByteBuffer attachment)");
        //ÿ�ζ�Ҫ����ע�������һ��ע�ᣬһ����Ӧ�����������ڡ��ļ�״̬��ʾ�����Ƕ���ģ����Բ���Ҫ�����С�©���ġ��¼�
        this.serverSocketChannel.accept(attachment, this);

        //Ϊ����µ�socketChannelע�ᡰread���¼����Ա����ϵͳ���յ����ݲ�׼���ú�����֪ͨӦ�ó���
        //�������������Ҫ������ͻ��˶�δ���������ۼ�����һ�����������ǽ�һ��stringbuffer������Ϊһ�������������������channel��
        //
        ByteBuffer readBuffer = ByteBuffer.allocate(50);
        socketChannel.read(readBuffer, new StringBuffer(), new SocketChannelReadHandle(socketChannel , readBuffer));
    }

    /* (non-Javadoc)
     * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
     */
    public void failed(Throwable exc, Void attachment) {
        ServerSocketChannelHandle.LOGGER.info("failed(Throwable exc, ByteBuffer attachment)");
    }
}

/**
 * �����ÿһ��socketChannel�����ݻ�ȡ�¼����м�����<p>
 * 
 * ��Ҫ��˵����һ��socketchannel������һ������������SocketChannelReadHandle����CompletionHandler�ӿڵ�ʵ�֣���
 * �����ֶ�������һ�����ļ�״̬��ʾ������FileDescriptor��
 * һ���������ɳ���Ա�����Buffer���棨��������ʹ�õ���ByteBuffer����
 * ���Բ��õ����ڷ������˻���֡��ܶ��������������ΪJAVA AIO����Ѿ�������֯���ˡ�<p>
 * 
 * ��������Ҫ�ģ���������channel�Ķ���AsynchronousChannelProvider�ǵ���ģʽ������������socketchannel��
 * ����һ���������ã�����û��ϵ����Ϊ������ֱ�Ӳ������AsynchronousChannelProvider���󣩡�
 * @author yinwenjie
 */
class SocketChannelReadHandle implements CompletionHandler<Integer, StringBuffer> {
    /**
     * ��־
     */
    private static final Log LOGGER = LogFactory.getLog(SocketChannelReadHandle.class);

    private AsynchronousSocketChannel socketChannel;

    /**
     * ר�����ڽ������ͨ�����ݻ��������ByteBuffer<br>
     * ��Ȼ����Ҳ������ΪCompletionHandler��attachment��ʽ���롣<br>
     * ���ǣ������ʾ�������У�attachment������������¼���д��͹�����Stringbuffer�ˡ�
     */
    private ByteBuffer byteBuffer;

    public SocketChannelReadHandle(AsynchronousSocketChannel socketChannel , ByteBuffer byteBuffer) {
        this.socketChannel = socketChannel;
        this.byteBuffer = byteBuffer;
    }

    /* (non-Javadoc)
     * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
     */
    public void completed(Integer result, StringBuffer historyContext) {
        //�������������˵���ͻ���������ֹ��TCP�׽��֣���ʱ�������ֹ�Ϳ�����
        if(result == -1) {
            try {
                this.socketChannel.close();
            } catch (IOException e) {
                SocketChannelReadHandle.LOGGER.error(e);
            }
            return;
        }

        SocketChannelReadHandle.LOGGER.info("completed(Integer result, Void attachment) : Ȼ��������ȡ��ͨ����׼���õ�ֵ");
        /*
         * ʵ���ϣ��������Ǵ�Integer result֪���˱���channel�Ӳ���ϵͳ��ȡ�����ܳ���
         * ����ʵ���ϣ����ǲ���Ҫ�л��ɡ���ģʽ���ģ�����Ϊ�˱�֤����Ĺ淶�ԣ����ǽ�������л���
         * 
         * ���⣬������JAVA AIO��ܻ���JAVA NIO��ܣ�������֡�buffer����������С�ڡ���ǰ�Ӳ���ϵͳ��ȡ����������������
         * �������ǣ�JAVA AIO����У����ǲ���Ҫר�ſ��Ǵ����������������ΪJAVA AIO����Ѿ����������˴��������˶��֪ͨ��
         * */
        this.byteBuffer.flip();
        byte[] contexts = new byte[1024];
        this.byteBuffer.get(contexts, 0, result);
        this.byteBuffer.clear();
        try {
            String nowContent = new String(contexts , 0 , result , "UTF-8");
            historyContext.append(nowContent);
            SocketChannelReadHandle.LOGGER.info("================Ŀǰ�Ĵ�������" + historyContext);
        } catch (UnsupportedEncodingException e) {
            SocketChannelReadHandle.LOGGER.error(e);
        }

        //�������������˵����û�н��յ���������ǡ�
        if(historyContext.indexOf("over") == -1) {
            return;
        }

        //=========================================================================
        //          ����ƪ���µĴ�����ͬ�������ԡ�over��������Ϊ�ͻ���������Ϣ�ı��
        //=========================================================================
        SocketChannelReadHandle.LOGGER.info("=======�յ�������Ϣ����ʼ����ҵ��=========");
        historyContext = new StringBuffer();

        //��Ҫ����������һ�μ���һ��֪ͨ��
        this.socketChannel.read(this.byteBuffer, historyContext, this);
    }

    /* (non-Javadoc)
     * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
     */
    public void failed(Throwable exc, StringBuffer historyContext) {
        SocketChannelReadHandle.LOGGER.info("=====���ֿͻ����쳣�رգ����������ر�TCPͨ��");
        try {
            this.socketChannel.close();
        } catch (IOException e) {
            SocketChannelReadHandle.LOGGER.error(e);
        }
    }
}
