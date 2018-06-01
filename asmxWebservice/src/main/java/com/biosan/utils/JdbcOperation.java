package com.biosan.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Jdbc工具封装的接口
 * 
 * @author qibill
 */
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
	 * @param <T>
	 * 
	 * @param sql
	 * @param params
	 * @return List<?>数据集合
	 * @throws SQLException
	 */
	<T> List<T> queryForBean(String sql, Object[] params, ResultSetMapper<T> mapper)
			throws SQLException;

	/**
	 * select功能
	 * @param <T>
	 * 
	 * @param sql
	 * @param params
	 * @return List<?>数据集合
	 * @throws SQLException
	 */
	<T> List<T> queryForBean(String sql, ResultSetMapper<T> mapper) throws SQLException;

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
//	int queryForInt(String sql, Object[] params) throws SQLException;

	/**
	 * select功能
	 * 
	 * @param sql
	 * @return 统计单列记录数
	 * @throws SQLException
	 */
//	int queryForInt(String sql) throws SQLException;

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
