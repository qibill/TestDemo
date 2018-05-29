package com.qibill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {
    public static void main(String[] args) {
    	String driverClass = "com.mysql.jdbc.Driver";
    	String jdbcUrl = "jdbc:mysql://172.16.0.73:33066/tower?characterEncoding=utf-8";
    	String user = "root";
    	String password = "biosan#17";

    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	
    	try {
    		//1、加载数据库驱动
    		Class.forName(driverClass);
    		//2、通过驱动管理类获取数据库链接
    		connection =  DriverManager.getConnection(jdbcUrl, user,password);
    		//3、定义sql语句 ?表示占位符
    	    String sql = "select * from department where departmentid = ?";
    		//4、获取预处理statement
    		preparedStatement = connection.prepareStatement(sql);
    		//5、设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
    		preparedStatement.setInt(1, 22);
    		//6、向数据库发出sql执行查询，查询出结果集
    		resultSet =  preparedStatement.executeQuery();
    		//7、遍历查询结果集
    		while(resultSet.next()){
    			System.out.println(resultSet.getString("departmentid")+"  "+resultSet.getString("departmentname"));
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally{
    		//8、释放资源
    		if(resultSet!=null){
    			try {
    				resultSet.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		if(preparedStatement!=null){
    			try {
    				preparedStatement.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    		if(connection!=null){
    			try {
    				connection.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    
    	}
    
    }
}
