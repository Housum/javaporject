package com.hotusm.concurrent.threadstate;

import java.util.concurrent.TimeUnit;

/**
 * Created by luqibao on 2016/12/26.
 * 下面处于不同的状态
 */
public class ThreadState {

    public static void main(String[] args){

        new Thread(new TimeWaiting()).start();
        new Thread(new Waiting()).start();

        new Thread(new Blocked()).start();
        new Thread(new Blocked()).start();

    }

    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while(true){

                SleepUtils.second(100);
            }
        }
    }

    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }

    static class SleepUtils{

        public static void second(long second){

            try {
                TimeUnit.SECONDS.sleep(second);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
