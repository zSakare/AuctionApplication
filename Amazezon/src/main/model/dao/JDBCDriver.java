package main.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class JDBCDriver {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String CONNECTION = "jdbc:postgresql://localhost:5923/AuctionDB";
	private static final String DB_USER = "auctionadmin";
	private static final String DB_PASSWORD = "admin";
	
	public Connection getConnection() {
		Connection conn = null;
    	try {
			InitialContext cxt = new InitialContext();
			
			DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );
			
			if ( ds == null ) {
			   throw new Exception("Data source not found!");
			}
	    	
	    	if (ds != null && cxt != null) {
	    		try {
	                Class.forName(DRIVER);
	            }
	            catch (java.lang.ClassNotFoundException e) {
	                java.lang.System.err.print("ClassNotFoundException: Postgres Server JDBC");
	                java.lang.System.err.println(e.getMessage());
	                throw new Exception("No JDBC Driver found in Server");
	            }
	    		
	    		try {
	    			conn = DriverManager.getConnection(CONNECTION, DB_USER, DB_PASSWORD);
	    			
	    			System.out.println("Connected");
	    		} catch (SQLException E) {
	                java.lang.System.out.println("SQLException: " + E.getMessage());
	                java.lang.System.out.println("SQLState: " + E.getSQLState());
	                java.lang.System.out.println("VendorError: " + E.getErrorCode());
	            }
	    	}
    	} catch (Exception e) {
    		System.err.println("Problem establishing connection: " + e);
    	}
		
		return conn;
	}
	
	public void doInsert() throws Exception {
		// To be filled by sub classes.
	}
}
