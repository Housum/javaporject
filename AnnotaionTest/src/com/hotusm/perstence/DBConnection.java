package com.hotusm.perstence;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.mysql.jdbc.Connection;


public class DBConnection {
	
	private static List<Connection> cons=new ArrayList<Connection>();
	//用来记录那些连接是没有被使用的
	private static Map<Integer,Boolean> map=new ConcurrentHashMap<Integer,Boolean>();
	private static String name="";
	private static String password="";
	private static String url="";
	private static String driver="";
	
	
	private DBConnection(){}
	static{
		
		InputStream in = DBConnection.class.getClassLoader().getSystemResourceAsStream("db.properties");
		Properties pro=new Properties();
		try {
			pro.load(in);
			name=pro.getProperty("db.username");
			password=pro.getProperty("db.password");
			url=pro.getProperty("db.url");
			driver=pro.getProperty("db.driver");
			Class.forName(driver);
			//设置默认的数据库连接池为50
			for(int i=0;i<10;i++){
				Connection connection = (Connection) DriverManager.getConnection(url,name,password);
				cons.add(connection);
				//全部设置为未使用状态
				map.put(i, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					for(int i=0;i<cons.size();i++){
						try {
							if(cons.get(i).isClosed()){
								Connection con=(Connection) DriverManager.getConnection(url,name,password);
								System.out.println("close...");
								cons.add(con);
								map.put(i, false);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();;
		
	}
	//获取连接
	public static Connection getConnection(){
		if(cons.size()>0){
			for(int i=0;i<cons.size();i++){
				if(map.get(i)==false){
					map.put(i, true);
					System.out.println("getConnection:"+i);
					return cons.get(i);
				}
			}
		}
		throw new RuntimeException("connection is busy");
	}
	//释放链接
	public static void releaseCon(Connection con){
		for(int i=0;i<cons.size();i++){
			if(con==cons.get(i)){
				if(map.get(i)==false){
					throw new RuntimeException("had release");
				}
				System.out.println("releaseCon:"+i);
				map.put(i, false);
			}
		}
	}
	
	
	public static void main(String[] args) {
		Connection con=getConnection();
		//con=getConnection();
		//con=getConnection();
		
		for(int i=0;i<9;i++){
			con=getConnection();
		}
		releaseCon(con);
	}
	
}
