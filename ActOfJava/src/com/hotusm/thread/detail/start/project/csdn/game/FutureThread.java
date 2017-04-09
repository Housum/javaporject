package com.hotusm.thread.detail.start.project.csdn.game;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * ������������ÿһ���˶�Ա�Ƿ��Ѿ�������
 * ��ΪFuture.get()��һֱ����  ֱ���������  ��ô����
 * �Ϳ���ʹ�������ʽ���ȴ����Ե����������
 * @author Hotusm
 *
 */
public class FutureThread extends Thread{

	private Future<Result> future;
	   /**
     * �ܲ��ɼ���������Ҫ�����Ķ���
     * ��Ҫ����Ӧ��ѡ�ּ��뵽���У��Ա����ݳɼ���������
     */
    private PriorityBlockingQueue<Player> achievementQueue;
    /**
     * ��ǰ�����ܲ���ѡ��
     */
    private Player player;
    public FutureThread(Future<Result> future , Player player , PriorityBlockingQueue<Player> achievementQueue) {
        this.future = future;
        this.player = player;
        this.achievementQueue = achievementQueue;
    }

    /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        // ����������������п��ܵľ���ѡ���ڱ��������У�
        // ����ĳ��ԭ�������ˣ�
        if(this.future == null) {
            System.out.println("ѡ���������Ʒ�Ϊ0");
        } else {
            try {
                // ���ѡ��û�����꣬FutureThread������������
                // ��Ȼ�����ܲ��������������ͻ��׳��쳣
                this.future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // ���е������˵�����ѡ�������ˣ����������ˣ�
        // ����ʲô�������������У�Ȼ��֪ͨ���߳�
        this.achievementQueue.put(this.player);
        synchronized (this.achievementQueue) {
            this.achievementQueue.notify();
        }
    }
}
