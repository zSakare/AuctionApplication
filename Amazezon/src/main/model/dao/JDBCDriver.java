package main.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCDriver {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String CONNECTION = "jdbc:postgresql://localhost:5923/AuctionDB";
	private static final String DB_USER = "auctionadmin";
	private static final String DB_PASSWORD = "admin";
	
	public Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not find driver. Check configurations. ClassNotFoundException: " + e);
		}
		
		try {
			connection = DriverManager.getConnection(CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			System.err.println("Could not establish connection. SQLException: " + e);
		}
		
		return connection;
	}
	
	public void doInsert() throws Exception {
		// To be filled by sub classes.
	}
}
