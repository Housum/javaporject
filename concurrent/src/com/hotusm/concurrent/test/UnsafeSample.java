package com.hotusm.concurrent.test;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 采用Unsafe构造院子的Object
 */
public class UnsafeSample {

    private volatile Object value=new Object();


    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            // 获取当前字段的内存位置  后来替换的地方需要使用
            valueOffset = unsafe.objectFieldOffset
                    (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public boolean compareAndSet(Object expect,Object newValue){
        return unsafe.compareAndSwapObject(this,valueOffset,expect,newValue);
    }

    public void setValue(Object newValue){

        //采用自旋的方式进行设置
        for(;;){
            Object tValue=value;
            if(compareAndSet(tValue,newValue)){

                return;
            }
        }
    }

    public Object getValue() {
        return value;
    }
}
