package com.qibill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.qibill.utils.DBCPUtil;

public class DBCPTest {
	
	@Test
	public void test1(){
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
		
		try {
			connection = DBCPUtil.getConnection();
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
//			...
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBCPUtil.release(connection, preparedStatement, resultSet);
		}
		
	}
}
