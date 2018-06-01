package com.biosan.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Jdbc的工具封装实现类
 * 
 * @author qibill
 */
public class JdbcUtil implements JdbcOperation {

	private DataSource dataSource;


	@Override
	public ResultSet queryForResultSet(String sql, Object[] params) throws SQLException {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = createPreparedStatement(connection, sql, params);
			return preparedStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(preparedStatement);
			free(connection);
		}
		return null;
	}

	@Override
	public ResultSet queryForResultSet(String sql) throws SQLException {
		return queryForResultSet(sql, new Object[] {});
	}

	@Override
	public List<Map<String, Object>> queryForMap(String sql, Object[] params) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = createPreparedStatement(conn, sql, params);
			rs = stmt.executeQuery();

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = null;
			ResultSetMetaData rsd = rs.getMetaData();
			int columnCount = rsd.getColumnCount();

			while (rs.next()) {
				map = new HashMap<String, Object>(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					map.put(rsd.getColumnLabel(i), rs.getObject(i));
				}
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(rs);
			free(stmt);
			free(conn);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForMap(String sql) throws SQLException {
		return queryForMap(sql, new Object[] {});
	}

/*	@Override
	public int queryForInt(String sql, Object[] params) throws SQLException {
		return 0;
	}*/

/*	@Override
	public int queryForInt(String sql) throws SQLException {
		return 0;
	}*/
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("服务器忙。。。");
		}
	}

	@Override
	public <T> List<T> queryForBean(String sql, Object[] params, ResultSetMapper<T> mapper)
			throws SQLException {
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<T> list = null;
		try {
			stmt = createPreparedStatement(conn, sql, params);
			rs = stmt.executeQuery();
			list = new ArrayList<>();
			list.addAll(mapper.putResult(rs));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			free(rs);
			free(stmt);
			free(conn);
		}
		return list;
	}

	@Override
	public <T> List<T> queryForBean(String sql, ResultSetMapper<T> mapper) throws SQLException {
		return queryForBean(sql, new Object[] {}, mapper);
	}
	
	/**
	 * 组建 sql 语句
	 * 
	 * @param conn		sql连接
	 * @param sql		sql语句
	 * @param params	替换通配符的娴熟
	 * @return
	 * @throws SQLException
	 * 
	 * @author qibill
	 * 2018年6月1日上午10:18:18
	 */
	private PreparedStatement createPreparedStatement(Connection conn, String sql, Object[] params)
			throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			stmt.setObject(i + 1, params[i]);
		return stmt;
	}
	
	/**
	 * 释放Connection资源
	 * 
	 * @param connection
	 * 
	 * @author qibill
	 * 2018年6月1日上午10:25:30
	 */
	private void free(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}

	/**
	 * 释放Statement资源
	 * 
	 * @param statement
	 * 
	 * @author qibill
	 * 2018年6月1日上午10:25:49
	 */
	private void free(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			statement = null;
		}
	}

	/**
	 * 释放PreparedStatement资源
	 * 
	 * @param preparedStatement
	 * 
	 * @author qibill
	 * 2018年6月1日上午10:26:20
	 */
	private void free(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			preparedStatement = null;
		}
	}

	/**
	 * 释放ResultSet资源
	 * 
	 * @param resultSet
	 * 
	 * @author qibill
	 * 2018年6月1日上午10:26:28
	 */
	private void free(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
	}
}
