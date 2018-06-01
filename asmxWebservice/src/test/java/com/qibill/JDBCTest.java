package com.qibill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.biosan.pojo.Patient;
import com.biosan.utils.JdbcUtil;
import com.biosan.utils.ResultSetMapper;

public class JDBCTest {
	
	@Test
	public void  jdbcUtilTest() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		String sql = "select * from patient where patientid = ?";
		Object[] params = {78408};
		List<Patient> patients = new ArrayList<>();
		ResultSetMapper<Patient> mapper = new ResultSetMapper<Patient>() {};
		try {
			patients = jdbcUtil.queryForBean(sql, params, mapper);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (patients.size() == 0) {
			System.out.println("么有数据");
		} else {
			System.out.println(patients.get(0));
		}
	}
	
	@Test
	public void  jdbcUtilTest1() {
		JdbcUtil jdbcUtil = new JdbcUtil();
		String sql = "select * from patient where patientid = ?";
		Object[] params = {78408};
		List<Patient> patients = new ArrayList<>();
		try {
			patients = jdbcUtil.queryForBean(sql, params, new ResultSetMapper<Patient>() {});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (patients.size() == 0) {
			System.out.println("么有数据");
		} else {
			System.out.println(patients.get(0));
		}
	}
	
	
	
	@Test
    public void jdbctest() {
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
