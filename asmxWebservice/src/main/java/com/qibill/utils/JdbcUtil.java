package com.qibill.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.biosan.utils.GetDataSource;

/**
 * DBCP配置类
 * 
 * @author qibill
 */
public class JdbcUtil implements JdbcOperation {

	private DataSource dataSource = null;

	{
		try {
			dataSource = GetDataSource.dbcpSources();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForMap(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryForInt(String sql, Object[] params) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int queryForInt(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void free(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			connection = null;
		}
	}

	@Override
	public void free(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			statement = null;
		}
	}

	@Override
	public void free(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			preparedStatement = null;
		}
	}

	@Override
	public void free(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
	}

	@Override
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

	private PreparedStatement createPreparedStatement(Connection conn, String sql, Object[] params)
			throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++)
			stmt.setObject(i + 1, params[i]);
		return stmt;
	}
}
