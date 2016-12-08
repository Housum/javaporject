package com.hotusm.designModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Hotusm
 * JAVA设计模式->观察者模式
 * 
 * 观察者模式就是一个类改变的时候,其他的类也需要改变(
 * 	这个关系是一对多的,即一个类改变了,有可能会影响到很多的类，这些类都要被通知
 * 
 * )
 */
//抽象观察者(Observer)角色
public abstract class Observer {

	public abstract void update(String state);
    public static void main(String[] args) {
		Subject s=new ConcreteSubject();
		s.attach(new ConcreteObserver());
		s.nodifyObservers("哈哈哈");
	}
}
//具体观察者(ConcreteObserver)角色
class ConcreteObserver extends Observer {
    //观察者的状态
    private String observerState;
    
    @Override
    public void update(String state) {
        /**
         * 更新观察者的状态，使其与目标的状态保持一致
         */
        observerState = state;
        System.out.println("Subject对象发生了改变,更在同步状态...");
        System.out.println("状态更新为："+observerState);
    }
    


}
//抽象主题(Subject)角色
abstract class Subject{
	
	List<Observer> list=new ArrayList<Observer>();
	
	/**
     * 注册观察者对象
     * @param observer    观察者对象
     */
    public void attach(Observer observer){
        
        list.add(observer);
        System.out.println("Attached an observer");
    }
    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(Observer observer){
        
        list.remove(observer);
    }
    /**
     * 通知所有注册的观察者对象
     */
    public void nodifyObservers(String newState){
        
        for(Observer observer : list){
            observer.update(newState);
        }
    }
}
//具体主题(ConcreteSubject)角色
class ConcreteSubject extends Subject{
		
	private String state;
	    
	    public String getState() {
	        return state;
	    }

	    public void change(String newState){
	        state = newState;
	        System.out.println("主题状态为：" + state);
	        //状态发生改变，通知各个观察者
	        this.nodifyObservers(state);
	    }	
}
