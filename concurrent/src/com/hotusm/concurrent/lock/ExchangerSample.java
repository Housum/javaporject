package com.hotusm.concurrent.lock;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by luqibao on 2017/1/4.
 * Exchanger适合两个线程之间的交互
 * exchange() 会一直堵塞  直到有另外的一个线程进行交互
 */
public class ExchangerSample {

    private static final Exchanger<String> exchanger=new Exchanger<>();

    private static final Executor executor=Executors.newCachedThreadPool();

    public static void main(String[] args){

        Runnable r0=()->{
            try{
               String a= exchanger.exchange("AAA");
                System.out.println(a);
            }catch (Exception e){
                e.printStackTrace();
            }
        };

        Runnable r1=()->{
            try{
               String b= exchanger.exchange("BBB");
               System.out.println(b);
            }catch (Exception e){
                e.printStackTrace();
            }
        };

        new Thread(r0).start();
        new Thread(r1).start();


    }
}
