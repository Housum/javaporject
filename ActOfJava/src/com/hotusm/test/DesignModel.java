package com.hotusm.test;

/**
 * 
 *��ʱ����֪��������ģʽ��ʲô  
 *1.��һ���ַ�Ϊ����߼�����   ���Ĵ�����ʵ�ֵ����Լ������б�д
 *ʵ��+�����ڲ���
 *@link org.springframework.jdbc.core.JdbcTemplate.execute(PreparedStatementCreator, PreparedStatementCallback<T>)
 *
 */
public class DesignModel {
	
	public interface Action{
		public void action(WordCount wc);
	}
	
	public void execute(Action action){
		WordCount wc=new WordCount();
		action.action(wc);
		wc=null;
	}
	
	public void count(final String fileName){
		execute(new Action(){
			@Override
			public void action(WordCount wc) {
				wc.count(fileName);
			}}
		);
	}
	public static void main(String[] args) {
		new DesignModel().count("C:/111.txt");
	}
}
