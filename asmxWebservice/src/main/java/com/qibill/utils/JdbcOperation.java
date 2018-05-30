package com.qibill.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public interface JdbcOperation {

	/**
	 * update或delete功能
	 * 
	 * @param sql
	 * @param params
	 * @return 变更记录数
	 * @throws SQLException
	 */
//	int execute(String sql, Object[] params) throws SQLException;

	/**
	 * update或delete功能
	 * 
	 * @param sql
	 * @return 变更记录数
	 * @throws SQLException
	 */
//	int execute(String sql) throws SQLException;

	/**
	 * 批处理update或delete功能
	 * 
	 * @param sql
	 * @param params
	 * @return 变更记录数
	 * @throws SQLException
	 */
//	int executeBatch(String sql, List<Object[]> params) throws SQLException;

	/**
	 * 批处理update或delete功能
	 * 
	 * @param sql
	 * @param params
	 * @return 变更记录数
	 * @throws SQLException
	 */
//	int executeBatch(String sql) throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @param params
	 * @return 原生ResultSet数据集合
	 * @throws SQLException
	 */
	ResultSet queryForResultSet(String sql, Object[] params) throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @return 原生ResultSet数据集合
	 * @throws SQLException
	 */
	ResultSet queryForResultSet(String sql) throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @param params
	 * @return List<?>数据集合
	 * @throws SQLException
	 */
//	List<?> queryForBean(String sql, Object[] params, RowMapper<?> mapper)
//			throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @param params
	 * @return List<?>数据集合
	 * @throws SQLException
	 */
//	List<?> queryForBean(String sql, RowMapper<?> mapper) throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @param params
	 * @return List<Map<String, Object>>数据集合
	 * @throws SQLException
	 */
	List<Map<String, Object>> queryForMap(String sql, Object[] params)
			throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @param params
	 * @return List<Map<String, Object>>数据集合
	 * @throws SQLException
	 */
	List<Map<String, Object>> queryForMap(String sql) throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @return 统计单列记录数
	 * @throws SQLException
	 */
	int queryForInt(String sql, Object[] params) throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @return 统计单列记录数
	 * @throws SQLException
	 */
	int queryForInt(String sql) throws SQLException;

	/**
	 * 释放Connection资源
	 * 
	 * @param connection
	 */
	void free(Connection connection);

	/**
	 * 释放Statement资源
	 * 
	 * @param statement
	 */
	void free(Statement statement);

	/**
	 * 释放PreparedStatement资源
	 * 
	 * @param preparedStatement
	 */
	void free(PreparedStatement preparedStatement);

	/**
	 * 释放ResultSet资源
	 * 
	 * @param resultSet
	 */
	void free(ResultSet resultSet);

	/**
	 * 设置数据源
	 * 
	 * @param dataSource
	 */
	void setDataSource(DataSource dataSource);

	/**
	 * 获取数据库链接
	 * 
	 * @return Connection
	 */
	Connection getConnection();

	/**
	 * 获取数据库链接
	 * 
	 * @param autoCommit
	 * @return Connection
	 */
//	Connection getConnection(boolean autoCommit);
}
