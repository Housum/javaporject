package com.hotusm.concurrent.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luqibao on 2016/12/28.
 */
public class LockTest {

    public static void main(String[] args){
         int SHARED_SHIFT   = 16;
         int SHARED_UNIT    = (1 << SHARED_SHIFT);
         int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
         // 10000000000000000 -1 =  0111111111111111
         int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

         System.out.println(1&EXCLUSIVE_MASK);

         System.out.println(EXCLUSIVE_MASK+"  "+(int)(Math.pow(2,16)-1));

         System.out.println(Integer.toBinaryString(65535)+"  "+Integer.toBinaryString(65535).length());

         System.out.println(Integer.toBinaryString(SHARED_UNIT));

         System.out.println(Math.pow(2,31));

         System.out.println(1>>>16);

         Lock readLock=new ReentrantLock();
         Lock writeLock=new ReentrantLock();

         List list=new ArrayList();

         synchronized (list){

         }

         //1.
      //   writeLock.lock();
         //2.
         writeLock.unlock();

         Object WRITE_LOCK=new Object();

         synchronized (WRITE_LOCK){
              try{
                   WRITE_LOCK.wait();
              }catch (Exception e){
                   e.printStackTrace();
              }
         }

         try{
              WRITE_LOCK.notifyAll();
         }catch (Exception e){}

    }
}
