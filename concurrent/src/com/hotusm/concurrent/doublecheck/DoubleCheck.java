package com.hotusm.concurrent.doublecheck;

/**
 * Created by luqibao on 2016/12/23.
 *
 */
public class DoubleCheck {

    private static volatile Instance instance;

    /**
     * 这种双检查的方式可以用下面的伪代码来展示过程
     * A：memory=allocate();分配对象的内存空间
     * B:ctorInstance(memory);初始化对象
     * C:instance=memory;设置instance指向刚分配的内存空间
     *
     * 上面的这个过程可能会被重排序为下面：
     * memory=allocate();
     * instance=memory;
     * ctorInstance(memory);
     *
     * 那么在下面的代码中就会判断instance不为null 直接将instance返回  但是
     * 返回的对象却没有分配内存空间
     *
     * 针对这种情况，如果要解决的话 那么从两方面来考虑：
     * 1.防止初始化的时候进行重排序
     * 2.重排序的过程对其他的线程不可见
     *
     * 对于1 可以直接使用volatile描述Instance 防止其进行重排序
     *
     *
     * 对于2 可以有下面的两种方式：
     *  ① @see InstanceHolder 在Class初始化的时候 会获取一个锁
     *  这个锁可以同步多个线程对类的出现华
     */
    public Instance getInstance(){

       // return InstanceHolder.instance;

        if(instance==null){
            synchronized (DoubleCheck.class){
                //这里可能因为发生冲排序  导致还没有初始化
                instance=new Instance();
            }
        }
        return instance;
    }

    private static class InstanceHolder{

        public static Instance instance=new Instance();
    }
}
