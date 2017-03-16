package com.hotusm.concurrent.test;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by luqibao on 2016/12/30.
 */
public class ABCSample {

    private static ReentrantLock lock=new ReentrantLock();

    private static volatile TreeMap<Integer,Node> indexs=new TreeMap<>();

    public static void main(String[] args){
        ReentrantLock lock=new ReentrantLock();
        TreeMap<Integer,Node> indexs=new TreeMap<>();
        Node node1=new Node("A",lock,indexs,0);
        Node node2=new Node("B",lock,indexs,1);
        Node node3= new Node("C",lock,indexs,2);
        indexs.put(0,node1);
        indexs.put(1,node2);
        indexs.put(2,node3);

        node1.beforeWork();
        node2.beforeWork();
        node3.beforeWork();


        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){

            e.printStackTrace();
        }

        node1.start();
        node2.start();
        node3.start();

        synchronized (ABCSample.class){

            try{
                ABCSample.class.wait();
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    private static class WorkQueue{

        private Node tail;
        private Node head;
    }

    private static class Node extends Thread{

        private static volatile boolean isRefresh=false;

        private volatile Map<Integer,Node> maps;
        private volatile boolean isWorked=false;
        private final int index; //索引

        private String message;
        private volatile Lock readLock;
        private boolean isLast=false;

        public Node(String message,Lock lock,Map<Integer,Node> maps,int index){
            this.message=message;
            this.readLock=lock;
            this.maps=maps;
            this.index=index;
        }

        public int getIndex() {
            return index;
        }

        public void beforeWork(){

            readLock.lock();
            try{
                if(index==maps.size()-1){
                    isLast=true;
                }else{
                    isLast=false;
                }
            }finally {
                readLock.unlock();
            }

        }
        @Override
        public void run() {
            while (true){
                readLock.lock();
                if(isRefresh){continue;}
                try{
                    if(this.index==0&&!this.isWorked){
                        System.out.print(this.message);
                        this.isWorked=true;
                    }else if(index!=0){
                        Node node= maps.get(index-1);
                        if(!node.isWorked){
                            System.out.print(node.message);
                            node.isWorked=true;
                        }else{
                            if(!this.isWorked){
                                System.out.print(this.message);
                                this.isWorked=true;
                            }else if(this.isWorked&&this.isLast){
                                refresh();
                            }
                        }
                    }
                }catch (Exception e){
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }finally {
                    readLock.unlock();
                }

                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

        private void refresh(){
            isRefresh=true;
            for(Map.Entry<Integer,Node> map:maps.entrySet()){
                map.getValue().isWorked=false;
            }
            isRefresh=false;
        }
    }
}
