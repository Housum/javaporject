package com.hotusm.concurrent.lock;

import com.hotusm.concurrent.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by luqibao on 2017/1/4.
 * 信号量  和池的概念非常像
 */
public class SemaphoreSample {

    public static void main(String[] args){

        final Semaphore semaphore=new Semaphore(2);

        new Thread(){
            @Override
            public void run() {
                try{
                    semaphore.acquire();
                    Logger.info("0acquire semaphore "+Thread.currentThread());
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try{
                    semaphore.acquire();
                    Logger.info("1acquire semaphore "+Thread.currentThread());
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try{
                    semaphore.acquire();
                    Logger.info("2acquire semaphore "+Thread.currentThread());
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();


            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
            }

            //可使用的数量
        System.out.println(semaphore.availablePermits());
            //等待队列中的数量
        System.out.println(semaphore.getQueueLength());
           //是否有线程等待许可证
        System.out.println(semaphore.hasQueuedThreads());



    }
}
