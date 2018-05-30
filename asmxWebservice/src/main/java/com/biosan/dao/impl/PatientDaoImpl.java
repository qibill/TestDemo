package com.biosan.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biosan.dao.PatientDao;
import com.biosan.pojo.Patient;
import com.qibill.utils.JdbcUtil;
import com.qibill.utils.ResultSetMapper;

public class PatientDaoImpl implements PatientDao {

	private JdbcUtil jdbcUtil;
	
	@Override
	public List<Patient> getPatientByPatientid(Integer patientid, Connection connection) {
		
		PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	
		if (connection == null) {
//			connection=JdbcUtil.getConnection();
		}
		try {
    	    String sql = "select * from patient where patientid = ?";
    		//4、获取预处理statement
    		preparedStatement = connection.prepareStatement(sql);
    		//5、设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
    		preparedStatement.setInt(1, patientid);
    		//6、向数据库发出sql执行查询，查询出结果集
    		resultSet =  preparedStatement.executeQuery();
    		//7、遍历查询结果集
    		ArrayList<Patient> putResult = ResultSetMapper.putResult(resultSet, Patient.class);
    		return putResult;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
		}
		return null;
	}

}
