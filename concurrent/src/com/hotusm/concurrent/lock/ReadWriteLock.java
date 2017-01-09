package com.hotusm.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by luqibao on 2016/12/29.
 * 读写锁
 * 1.一旦写锁被获取的话  那么其他线程不能获取读写锁
 * 2.在写锁没被获取的时候 ，读锁可以被获取多次
 * 3.当读锁还没被释放的时候,不能够获取到写锁
 */
public class ReadWriteLock {

    public static void main(String[] args){

        Cache cache=new Cache();

    }

    private static class Cache{

        private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
        private final Lock readLock=lock.readLock();
        private final Lock writeLock=lock.writeLock();

        private final Map<String,Object> caches=new HashMap<String,Object>();//not thread safe


        public void put(String key,Object value){
            writeLock.lock();
            try{
                if(caches.containsKey(key)){
                    Log.info("key :"+key + " 包含旧值 刷新旧值");
                }
                caches.put(key,value);
                Log.info("保存缓存 key: "+key+" value: "+value);
            }finally {
                writeLock.unlock();
            }
        }

        public Object get(String key){
            readLock.lock();
            try{
                Log.info("获取缓存 key: "+key);
                return caches.get(key);
            }finally {
                readLock.unlock();
            }
        }

        public int getWriteCacheCount(){

            return lock.getWriteHoldCount();
        }

        public int getReadCacheCount(){

            return lock.getReadLockCount();
        }

        public boolean canWrite(){

            return lock.isWriteLocked();
        }

    }

    private static class Log{

        public static void info(String message){

            System.out.println("TS: "+System.currentTimeMillis()/1000+" logMsg:"+message);
        }
    }
}
