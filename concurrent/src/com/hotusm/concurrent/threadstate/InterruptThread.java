package com.hotusm.concurrent.threadstate;

import java.util.concurrent.TimeUnit;

/**
 * Created by luqibao on 2016/12/26.
 * Java 的API中 许多声明抛出InterruptException的方法
 * 这些方法在抛出异常之后 虚拟机会将中断表示给清除
 * 所以我们需要自行处理这种情况
 */
public class InterruptThread {

    public static void main(String[] args){

       Thread thread= new Thread(new RunnableImpl());
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){

        }
        thread.interrupt();


    }

    private static class RunnableImpl implements Runnable{

        @Override
        public void run() {

            try{
                //在WAITING 和TIME_WAITING的情况下   如果出现被中断的时候  那么就会
                //抛出 InterruptException 并且将中断状态清除
                TimeUnit.SECONDS.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            //在调用方法interrupt 的时候  这句话会被打印
            System.out.println("interrupt");
        }
    }

}
