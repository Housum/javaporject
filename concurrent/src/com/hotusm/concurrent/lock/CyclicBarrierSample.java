package com.hotusm.concurrent.lock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by luqibao on 2017/1/4.
 *
 * CountDownLatch 是一次性的  但是CyclicBarrier可以通过reset多次的使用
 * 并且还有一些其他的比较实用的方法 isBroken() 判断是否有被中断
 * getNumberWaiting() 刊有多少个线程在等待
 *
 */
public class CyclicBarrierSample {

    public static void main(String[] args){

        final CyclicBarrier cyclicBarrier=new CyclicBarrier(3);

        Thread thread=new Thread(){
            @Override
            public void run() {

                try{
                    cyclicBarrier.await();
                }catch (Exception e){
                  //  e.printStackTrace();
                }
            }
        };


        thread.start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){}
        System.out.println("wait num: "+cyclicBarrier.getNumberWaiting());
        thread.interrupt();


        try{
            cyclicBarrier.await();
        }catch (Exception e){
            //判断是否有中断
            System.out.print(cyclicBarrier.isBroken());
        }

    }
}
