package com.hotusm.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by luqibao on 2016/12/23.
 *  AQS是基于validate的内存可见性实现的
 *  @see java.util.concurrent.locks.AbstractQueuedSynchronizer
 */
public class SeralThread {

    public static void main(String[] args){

        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();


        synchronized (SeralThread.class){

            try{
                SeralThread.class.wait();
            }catch (Exception e){
                    e.printStackTrace();
            }

        }

        //System.out.print("哈".matches("[(\\u4e00-\\u9fa5)(\\()(\\))(\\（)(\\）)]+"));

    }
}

//如果采用并行的方式 那么会快很多
 class TestThread implements Runnable{

    private static volatile int state=0;

    private static int index=0;

    private static long startTime=System.currentTimeMillis();

     @Override
     public void run() {

         while (true){

             //不相互影响的业务逻辑消耗的时间
             try {
                 TimeUnit.MICROSECONDS.sleep(1L);
             }catch (Exception e){

                 e.printStackTrace();
             }

             //对资源有争夺的逻辑
             if(tryLock()){
                 try{
                     System.out.print("A");
                     System.out.println("B");
                     index++;
                     if(index>10000){
                         long endTime=System.currentTimeMillis();
                         System.out.println(endTime-startTime);
                         break;
                     }
                 }finally {
                     tryRelease();
                 }
             }
         }

     }

     //因为在内存中  validate的state是可见的 所以可以使用这个来判断
     private boolean tryLock(){
         if(state!=0){
             return false;
         }else {
             state=1;
             return true;
         }
     }

     //释放锁 就是将state设置为0 表示没有线程在使用
     private boolean tryRelease(){
         if(state==0){
             return false;
         }else{
             state=0;
             return true;
         }
     }
 }

