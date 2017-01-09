package com.hotusm.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by luqibao on 2016/12/29.
 * park 是堵塞当前的线程
 * unpark 是解除堵塞
 *
 */
public class LockSupportSample {

    public static void main(String[] args){

        //LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));

        Thread thread=  new Thread(){
            @Override
            public void run() {
                System.out.println("starting park");
                //当前线程进入堵塞
                LockSupport.park();
                System.out.println("over ");
            }
        };

        thread.start();

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }

        //结束线程的堵塞状态
        LockSupport.unpark(thread);

       // System.out.println("finish...");

        //第一个参数表示的是在哪个对象上面进行等待
        //LockSupport.parkNanos(LockSupportSample.class,TimeUnit.SECONDS.toNanos(3));

        //防止主线程退出
        synchronized (LockSupportSample.class){

            try{
                LockSupportSample.class.wait();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
