package com.hotusm.test;

/**
 * 
 *暂时还不知道这个设计模式是什么  
 *1.将一部分分为最好逻辑处理   核心代码由实现的人自己来进行编写
 *实现+匿名内部类
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
