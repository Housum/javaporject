package com.hotusm.concurrent.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用
 *
 */
public class ThreadPoolExecutorSample {

    public static void main(String[] args){
        test_1();
    }

    public static void test_1(){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10,30,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {

                System.out.println(111);
            }
        });

        threadPoolExecutor.shutdown();
    }
}
