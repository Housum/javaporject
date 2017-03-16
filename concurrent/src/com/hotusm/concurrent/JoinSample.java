package com.hotusm.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by luqibao on 2016/12/30.
 */
public class JoinSample {

    public static void main(String[] args){

        Thread thread=new Thread(){
            @Override
            public void run() {

                System.out.println("---");
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        //A 在B 线程中执行a.join()  表示的是B要等待A执行完成之后 B线程才开始执行
        try {
            thread.join();
        }catch (Exception e){

            e.printStackTrace();
        }


    }
}
