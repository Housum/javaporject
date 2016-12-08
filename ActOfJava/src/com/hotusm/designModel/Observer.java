package com.hotusm.designModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Hotusm
 * JAVA���ģʽ->�۲���ģʽ
 * 
 * �۲���ģʽ����һ����ı��ʱ��,��������Ҳ��Ҫ�ı�(
 * 	�����ϵ��һ�Զ��,��һ����ı���,�п��ܻ�Ӱ�쵽�ܶ���࣬��Щ�඼Ҫ��֪ͨ
 * 
 * )
 */
//����۲���(Observer)��ɫ
public abstract class Observer {

	public abstract void update(String state);
    public static void main(String[] args) {
		Subject s=new ConcreteSubject();
		s.attach(new ConcreteObserver());
		s.nodifyObservers("������");
	}
}
//����۲���(ConcreteObserver)��ɫ
class ConcreteObserver extends Observer {
    //�۲��ߵ�״̬
    private String observerState;
    
    @Override
    public void update(String state) {
        /**
         * ���¹۲��ߵ�״̬��ʹ����Ŀ���״̬����һ��
         */
        observerState = state;
        System.out.println("Subject�������˸ı�,����ͬ��״̬...");
        System.out.println("״̬����Ϊ��"+observerState);
    }
    


}
//��������(Subject)��ɫ
abstract class Subject{
	
	List<Observer> list=new ArrayList<Observer>();
	
	/**
     * ע��۲��߶���
     * @param observer    �۲��߶���
     */
    public void attach(Observer observer){
        
        list.add(observer);
        System.out.println("Attached an observer");
    }
    /**
     * ɾ���۲��߶���
     * @param observer    �۲��߶���
     */
    public void detach(Observer observer){
        
        list.remove(observer);
    }
    /**
     * ֪ͨ����ע��Ĺ۲��߶���
     */
    public void nodifyObservers(String newState){
        
        for(Observer observer : list){
            observer.update(newState);
        }
    }
}
//��������(ConcreteSubject)��ɫ
class ConcreteSubject extends Subject{
		
	private String state;
	    
	    public String getState() {
	        return state;
	    }

	    public void change(String newState){
	        state = newState;
	        System.out.println("����״̬Ϊ��" + state);
	        //״̬�����ı䣬֪ͨ�����۲���
	        this.nodifyObservers(state);
	    }	
}
