package com.hotusm.third.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.net.InetSocketAddress;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class TestTCPNetty {

	 static {
	        BasicConfigurator.configure();
	    }

	    public static void main(String[] args) throws Exception {
	        //�������Ҫ�ķ���������
	        ServerBootstrap serverBootstrap = new ServerBootstrap();

	        //=======================�������������̳߳�
	        //BOSS�̳߳�
	        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
	        //WORK�̳߳أ�������������ʽ����Ҫ��Ϊ�������˵��Netty���߳���������������
	        ThreadFactory threadFactory = new DefaultThreadFactory("work thread pool");
	        //CPU����
	        int processorsNumber = Runtime.getRuntime().availableProcessors();
	        EventLoopGroup workLoogGroup =new NioEventLoopGroup(processorsNumber*2, (Executor) threadFactory, SelectorProvider.provider());
	        //ָ��Netty��Boss�̺߳�work�߳�
	        serverBootstrap.group(bossLoopGroup , workLoogGroup);
	        //��������µ�������ʽ��˵��BOSS�̺߳�WORK�̹߳���һ���̳߳�
	        //��ʵ����һ�����������£����ֹ����̳߳صķ�ʽ�Ѿ����ˣ�
	        //serverBootstrap.group(workLoogGroup);

	        //========================���������������Ƿ����ͨ������
	        //ֻ����ʵ����ServerChannel�ӿڵġ���������ͨ����
	        serverBootstrap.channel(NioServerSocketChannel.class);
	        //��ȻҲ���������������Ǹ�SelectorProvider�ǲ��Ǹо�����Ϥ����
	        //serverBootstrap.channelFactory(new ChannelFactory<NioServerSocketChannel>() {
	        //  @Override
	        //  public NioServerSocketChannel newChannel() {
	        //      return new NioServerSocketChannel(SelectorProvider.provider());
	        //  }
	        //});

	        //========================���ô�����
	        //Ϊ����ʾ����������������һ��򵥵�ByteArrayDecoder��ByteArrayEncoder
	        //Netty����ɫ������һ������ͨ��ˮ�ܡ��еġ���������
	        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
	            /* (non-Javadoc)
	             * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
	             */
	            @Override
	            protected void initChannel(NioSocketChannel ch) throws Exception {
	                ch.pipeline().addLast(new ByteArrayEncoder());
	                ch.pipeline().addLast(new TCPServerHandler());
	                ch.pipeline().addLast(new ByteArrayDecoder());
	            }
	        });

	        //========================����netty�������󶨵�ip�Ͷ˿�
	        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
	        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
	        serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 83));
	        //�����Լ�ض���˿�
	        //serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 84));
	    }
}
/**
 * @author yinwenjie
 */
@Sharable
class TCPServerHandler extends ChannelHandlerAdapter {
    /**
     * ��־
     */
    private static Log LOGGER = LogFactory.getLog(TCPServerHandler.class);

    /**
     * ÿһ��channel�����ж�����handler��ChannelHandlerContext��ChannelPipeline��Attribute
     * ���Բ���Ҫ���Ķ��channel�е���Щ�����໥Ӱ�졣<br>
     * ��������ʹ��content���key����¼���handler���Ѿ����յ��Ŀͻ�����Ϣ��
     */
    private static AttributeKey<StringBuffer> content = AttributeKey.valueOf("content");

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRegistered(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelRegistered(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelUnregistered(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelUnregistered(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelActive(ctx) = " + ctx.toString());
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelInactive(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TCPServerHandler.LOGGER.info("channelRead(ChannelHandlerContext ctx, Object msg)");
        /*
         * ����ʹ��IDE����ģ�ⳤ�����е����ݻ����ύ��
         * ��read��������������ݣ���ֻ�ǽ��������ۼӣ��������κδ���
         * */
        ByteBuf byteBuf = (ByteBuf)msg;
        try {
            StringBuffer contextBuffer = new StringBuffer();
            while(byteBuf.isReadable()) {
                contextBuffer.append((char)byteBuf.readByte());
            }

            //������ʱ����
            StringBuffer content = ctx.attr(TCPServerHandler.content).get();
            if(content == null) {
                content = new StringBuffer();
                ctx.attr(TCPServerHandler.content).set(content);
            }
            content.append(contextBuffer);
        } catch(Exception e) {
            throw e;
        } finally {
            byteBuf.release();
        }
    } 

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelReadComplete(ChannelHandlerContext ctx)");
        /*
         * ��readComplete���������������Ƿ�������ˡ�
         * ��֮ǰ������һ�������Ǽ�������������Ƿ��С�over���ؼ���
         * */
        StringBuffer content = ctx.attr(TCPServerHandler.content).get();
        //�����������˵����û�н��յ������ͻ�����Ϣ
        if(content.indexOf("over") == -1) {
            return;
        }

        //�����յ���Ϣ������Ҫ���ĵ������ԭ������ʷ��Ϣ
        ctx.attr(TCPServerHandler.content).set(new StringBuffer());

        //׼����ͻ��˷�����Ӧ
        ByteBuf byteBuf = ctx.alloc().buffer(1024);
        byteBuf.writeBytes("�ط���Ӧ��Ϣ��".getBytes());
        ctx.writeAndFlush(byteBuf);

        /*
         * �رգ�������ֹ���ͨ�������ģ��Ϳ��Թر�ͨ����
         * ��������رգ����ͨ���Ļػ���һֱ���ڣ�ֻҪ�������ȶ��ģ��������Ϳ�����ʱͨ������ػ���ͻ��˷�����Ϣ����
         * �ر�ͨ����ζ��TCP�������Ͽ����������е�
         * handler��ChannelHandlerContext��ChannelPipeline��Attribute����Ϣ����ע��
         * */
        ctx.close();
    } 

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        TCPServerHandler.LOGGER.info("super.userEventTriggered(ctx, evt)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelWritabilityChanged(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelWritabilityChanged(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        TCPServerHandler.LOGGER.info("super.exceptionCaught(ctx, cause)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelHandlerAdapter#handlerAdded(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.handlerAdded(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelHandlerAdapter#handlerRemoved(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.handlerRemoved(ctx)");
    }
}
