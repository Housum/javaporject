package com.hotusm.concurrent.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * Created by luqibao on 2017/1/3.
 */
public class ABCSample2 {


    public static void main(String[] args){

       WorkQueue queue= new WorkQueue();
       queue.addWork("A");
       queue.addWork("B");
       queue.addWork("C");
       queue.addWork("D");
       queue.addWork("E");

       queue.startWork();


        synchronized (ABCSample2.class){

            try{
                ABCSample2.class.wait();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    private static class WorkQueue{

        private Node head;
        private Node tail;
        private int num=0;

        private static final Unsafe unsafe = getUnsafe();
        private static final long tailOffset;
        private static final long headOffset;

        static {
            try {
                // 获取当前字段的内存位置  后来替换的地方需要使用
                headOffset = unsafe.objectFieldOffset
                        (WorkQueue.class.getDeclaredField("head"));
                tailOffset = unsafe.objectFieldOffset
                        (WorkQueue.class.getDeclaredField("tail"));
            } catch (Exception ex) { throw new Error(ex); }
        }

        private static Unsafe getUnsafe() {
            try {

                Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
                singleoneInstanceField.setAccessible(true);
                return (Unsafe) singleoneInstanceField.get(null);

        } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        protected void push(Node node){

            for (;;) {
                Node t = tail;
                if (t == null) {
                    if (compareAndSetHead(node))
                        num++;
                    tail = head;
                    break;
                } else {
                    if (compareAndSetTail(t, node)) {
                        num++;
                        t.next = node;
                        break;
                    }
                }
            }
        }

        /**
         *加入工作的顺序就是打印的顺序
         * @param message  打印的信息
         */
       public void addWork(String message){
            Node node=new Node(message);
            push(node);
        }

        /**
         * 开始进行工作
         */
        public void startWork(){
            Node.head=this.head;
            Node.tail=tail;
            Node.latch=new CountDownLatch(num);
            Node.currentWork=head;

            for (Node node=head;node!=null;node=node.next){
                node.start();
                Node.latch.countDown();
            }
        }


        public boolean compareAndSetTail(Node expect,Node newValue){
            return unsafe.compareAndSwapObject(this,tailOffset,expect,newValue);
        }
        public boolean compareAndSetHead(Node expect){
            return unsafe.compareAndSwapObject(this,headOffset,null,expect);
        }

    }


    private static class Node extends Thread{

        private static volatile Node currentWork;

        private static volatile Node head;
        private static volatile Node tail;
        private static volatile CountDownLatch latch;

        private String message;
        private Node next;


        public Node(String message) {
            this.message = message;
        }

        @Override
        public void run() {

            try {
                latch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            for(;;){
                if(currentWork==this){
                    if(!"".equals(message)){
                        System.out.print(message);
                    }
                    if(this==tail){
                        currentWork=head;
                    }else{
                        currentWork=this.next;
                    }
                }
            }

        }
    }
}
