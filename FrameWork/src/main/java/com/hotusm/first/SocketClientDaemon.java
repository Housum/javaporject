package com.hotusm.first;

import java.util.concurrent.CountDownLatch;

public class SocketClientDaemon {

	  public static void main(String[] args) throws Exception {
	        Integer clientNumber = 20;
	        CountDownLatch countDownLatch = new CountDownLatch(clientNumber);

	        //�ֱ�ʼ������20���ͻ���
	        for(int index = 0 ; index < clientNumber ; index++ , countDownLatch.countDown()) {
	            SocketClientRequestThread client = new SocketClientRequestThread(countDownLatch, index);
	            new Thread(client).start();
	        }

	        //���wait���漰�������ʵ���߼���ֻ��Ϊ�˱�֤�ػ��߳������������̺߳󣬽���ȴ�״̬
	        synchronized (SocketClientDaemon.class) {
	            SocketClientDaemon.class.wait();
	        }
	    }
}
