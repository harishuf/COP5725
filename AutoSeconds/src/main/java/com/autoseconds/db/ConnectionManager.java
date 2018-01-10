package com.autoseconds.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class ConnectionManager {

	private static final String USERNAME = "manoj";
	private static final String PASSWORD = "Praana#1";
	private static final String JDBC_ORACLE_CONN = "jdbc:oracle:thin:@oracle.cise.ufl.edu:1521:orcl";
	private static Connection connection = null;
	private static ConnectionManager connectionManager = null;

	private ConnectionManager() {
		/*
		 * DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
		 * .url(JDBC_ORACLE_CONN).username(USERNAME).password(PASSWORD);
		 * dataSource = dataSourceBuilder.build();
		 */
		/*
		 * try { connection = DriverManager.getConnection(JDBC_ORACLE_CONN,
		 * USERNAME, PASSWORD); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}

	public synchronized static ConnectionManager getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		} else {
			System.out.println("Fetching connection");
			connection = DriverManager.getConnection(JDBC_ORACLE_CONN, USERNAME, PASSWORD);
			return connection;
		}
	}

	public void closeConnection(Connection connection) throws SQLException {
		if (connection != null) {
			System.out.println("Closing connection");
			connection.close();
		}
	}
}
