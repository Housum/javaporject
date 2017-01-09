package com.hotusm.concurrent.lock;

import com.hotusm.concurrent.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luqibao on 2016/12/29.
 * Condition的用法
 * 实用Condition的之前  需要先获取创建这个Condition的Lock对象
 * @see  java.util.concurrent.ArrayBlockingQueue 队列中的出队和入队
 * 使用的是两个Condition  notEmpty 和 notFull
 */
public class ConditionSample {

    public static void main(String[] args){



      final Subscription subscription=new Subscription();

        new Thread(){
            @Override
            public void run() {
                Logger.info("订阅key 为1的 缓存");
                subscription.sub("1");
                Logger.info("订阅成功 key为1的缓存");

            }
        }.start();

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(){

            @Override
            public void run() {
                subscription.pro("2","1");
            }
        }.start();

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(){

            @Override
            public void run() {
                subscription.pro("1","1");
            }
        }.start();

      synchronized (ConditionSample.class){

            try{
                ConditionSample.class.wait();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }


    private static class Subscription{

        private final Lock lock=new ReentrantLock();
        private final Condition condition=lock.newCondition();

        private ConcurrentHashMap<String,Object> maps=new ConcurrentHashMap<>();

        public Object sub(String key){

            Logger.info(String.format("获取key %s 的值",key));
            if(maps.containsKey(key)){
                return maps.get(key);
            }

            Object value=null;
            while(value==null){
                lock.lock();
                try{
                    condition.await();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                value=maps.get(key);
            }
            Logger.info(String.format("获取成功 key:%s value: %s",key,value==null?"":value.toString()));
            return value;
        }

        public void pro(String key,Object value){
            lock.lock();
            Logger.info(String.format("储存key 为 %s value 为%s 的数据",key,value));
            try{
                maps.put(key,value);
                condition.signalAll();
            }finally {
                lock.unlock();
            }

        }
    }
}
