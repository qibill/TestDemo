package com.biosan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biosan.dao.PatientDao;
import com.biosan.pojo.Patient;
import com.qibill.utils.DBCPUtil;

public class PatientDaoImpl implements PatientDao {

	@Override
	public Patient getPatientByIdentitycard(String identitycard, Connection connection) {
		
		PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	
		if (connection == null) {
			connection=DBCPUtil.getConnection();
		}
		try {
    	    String sql = "select * from patient where identitycard = ?";
    		//4、获取预处理statement
    		preparedStatement = connection.prepareStatement(sql);
    		//5、设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
    		preparedStatement.setString(1, identitycard);
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
		return null;
	}

}
