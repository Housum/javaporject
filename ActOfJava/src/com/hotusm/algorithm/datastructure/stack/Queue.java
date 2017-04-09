package com.hotusm.algorithm.datastructure.stack;

public class Queue<T> {
	
  private Entry<T> first;
  private Entry<T> last;
  
  public Queue(){
	  first=new Entry<T>(null,null);
	  last=first;
  }
  public void offer(T data){
	 Entry<T> entry=new Entry<T>(null,data);
	 last.next=entry;
	 last=entry;
  }
  
  public T poll(){
	  if(last==first){
	  }
	  T data=first.next.data;
	  first=first.next;
	  return data;
  }
 
  private static class Entry<T>{
	  
	   private Entry<T> next;
	   private T data;
	   
	   public Entry(Entry next,T data){
		   this.next=next;
		   this.data=data;
	   }
  }
  
  public static void main(String[] args) {
	
	  Queue<String> queue=new Queue();
	  queue.offer("1");
	  queue.offer("2");
	  queue.offer("3");
	  System.out.println(queue.poll());
	  System.out.println(queue.poll());
	  System.out.println(queue.poll());
	  System.out.println(queue.poll());
  }
}
