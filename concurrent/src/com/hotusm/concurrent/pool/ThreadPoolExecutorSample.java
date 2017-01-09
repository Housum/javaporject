package com.hotusm.concurrent.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的使用
 *
 *
 */
public class ThreadPoolExecutorSample {

    public static void main(String[] args){
      //  test_1();
      //  test_2();
        test_3();
    }

    /**
     * 各个参数的含义：
     *  int corePoolSize,  核心的线程数
        int maximumPoolSize, 最大的线程数
        long keepAliveTime,  空闲线程保留的时间
        TimeUnit unit, 时间的单位
        BlockingQueue<Runnable> workQueue)  工作队列
     *
     */
    public static void test_1(){
        int cpuNum=Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(cpuNum,cpuNum*2,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

        threadPoolExecutor.execute(()->{
            System.out.println("pool...");
        });

        threadPoolExecutor.shutdown();
    }

    /**
     * 参数：
     * ThreadFactory 创建线程的工厂
     *
     */
    public static void test_2(){

        ThreadFactory factory=new ThreadFactoryBuilder().setNameFormat("Task-%d").build();
        int cpuNum=Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(cpuNum,cpuNum*2,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10),factory);

        threadPoolExecutor.execute(()->{
            System.out.println("pool...");
        });

        threadPoolExecutor.shutdown();

    }

    /**
     *    RejectedExecutionHandler 对超过了最大的线程池的丢弃状态
     */
    private static void test_3(){

        ThreadFactory factory=new ThreadFactoryBuilder().setNameFormat("Task-%d").build();
        int cpuNum=Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(cpuNum,cpuNum*2,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10),factory,(r,e)->{
            //停留10毫秒 继续执行任务
            try{
                TimeUnit.MILLISECONDS.sleep(10);
            }catch (Exception e1){
                Thread.currentThread().interrupt();
            }
            e.execute(r);
        });

        for (int i=0;i<100;i++){
            threadPoolExecutor.execute(()->{
                System.out.println("Task ");
                //模拟任务花费的时间
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (Exception e1){
                    Thread.currentThread().interrupt();
                }
            });
        }
        monitor(threadPoolExecutor);

        threadPoolExecutor.shutdown();
    }

    /**
     * 几个比较重要的参数
     * poolSize： 队列中存活的线程(包含已经完成任务但是没有销毁的)
     * activeCount： 在队列中还在执行任务的线程数
     * completedTaskCount 完成的任务
     * largestPoolSize  队列中峰值
     * taskCount:任务总数
     * @param e
     */
    private static void monitor(final ThreadPoolExecutor e){

        new Thread(()->{
            while (true){
                System.out.println(String.format("maxPoolSize:%d,poolSize: %d,activeCount: %d,completedTaskCount: %d," +
                                "largestPoolSize: %d,taskCount: %d",
                        e.getMaximumPoolSize(),e.getPoolSize(),e.getActiveCount(),e.getCompletedTaskCount(),
                        e.getLargestPoolSize(),e.getTaskCount()));

                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e1){
                    Thread.currentThread().interrupt();
                }

            }
        }).start();


    }
}
