package com.hotusm.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 根据AQS 实现共享锁
 */
public class TwinsLock implements Lock{

    private static class Sync extends AbstractQueuedSynchronizer{

        public Sync(int num){
            setState(num);
        }

        @Override
        protected int tryAcquireShared(int arg) {

            for(;;){
                int current=getState();
                int newCount=current-arg;

                /*
                    这里比较难理解: 因为我们继承的这个方法是一个模板方法,在AQS中
                    是在方法doAcquireShared中进行调用,根据这个方法的返回值来进行判断
                    是否获得锁,如果>0 那么就认为是获得锁 否则继续等.下面的逻辑进行这样的判断：
                    如果是<0 那么就直接将<0的值返回了,>0 那么不仅将值返回 而且将state的值进行更新(至于锁是否获得,
                   ,不需要我们考虑是我们返回的值给AQS中进行)
                */
                if(newCount<0||compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for(;;){

                int current=getState();
                int newCount=arg+current;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }

      Condition newCondition(){
           return new ConditionObject();
      }

    }

    private final Sync sync=new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1)>0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {

        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public static void main(String[] args){

       final Lock lock=new TwinsLock();


        class  ThreadExt extends Thread{
               @Override
               public void run() {

                   while (true){
                       try{
                           lock.lock();
                           System.out.println("Thread:"+Thread.currentThread().getName());
                           TimeUnit.SECONDS.sleep(10);
                       }catch (Exception e){
                           e.printStackTrace();
                       }finally {
                           lock.unlock();
                       }
                   }
               }
           }

           for (int i=0;i<10;i++){

                ThreadExt ext=new ThreadExt();
                ext.start();
           }

    }
}
