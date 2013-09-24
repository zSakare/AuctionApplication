package main.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends JDBCDriver {
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private String dob;
	private String creditCard;
	
	public UserDAO(String username, 
			String password, 
			String email, 
			String firstname,
			String lastname, 
			String address,
			String dob, 
			String creditCard) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.dob = dob;
		this.creditCard = creditCard;
	}
	
	@Override
	public void doInsert() {
		Statement sqlStatement = null;
		Connection connection = null;
		
		//TODO: INCLUDE EMAIL.
		String sql = "INSERT INTO USERS " +
				"(firstname,lastname,username,password,address,yearOfBirth,creditCard,confirmed) " +
				"VALUES ('" + firstname + "','" + lastname + "','" + username + "','" + password + "'," +
						"'" + address + "','" + dob + "','" + creditCard + "','false');";
		
		try {
			connection = getConnection();
			sqlStatement = connection.createStatement();
			
			sqlStatement.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("Problem attempting to insert user record. SQLException: " + e);
		} finally {
			try {
				if (sqlStatement != null) {
					sqlStatement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				// Ignore.
			}
		}
	}
}
