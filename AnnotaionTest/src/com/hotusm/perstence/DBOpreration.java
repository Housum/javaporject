package com.hotusm.perstence;

import java.io.ObjectInputStream.GetField;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBOpreration {
	
	public static void getStatement(String sql,String... params) throws SQLException{
		
		Connection connection = DBConnection.getConnection();
		PreparedStatement prepareStatement =(PreparedStatement) connection.prepareStatement(sql);
		if(params!=null){
			for(int i=0;i<params.length;i++){
				System.out.println("---"+i);
				System.out.println(params[i]);
				prepareStatement.setString(i+1, params[i]);
			}	
		}
		prepareStatement.executeUpdate();
	}
	
	public static void main(String[] args){
//		String sql="create table cc(id int)";
//		try {
//			getStatement(sql, null);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		try {
			Connection connection = DBConnection.getConnection();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
