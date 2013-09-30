package main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDAO extends JDBCDriver {
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String address;
	private int dob;
	private String creditCard;
	
	public UserDAO(String username, 
			String password, 
			String email, 
			String firstname,
			String lastname, 
			String address,
			int dob, 
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
	public void doInsert() throws Exception {
		
		Connection conn = null;
		
		//TODO: STOP SQL INJECTION LOLOL
		String sql = "INSERT INTO USERS (firstname,lastname,username,password,email,address,yearOfBirth,creditCard,confirmed) VALUES (?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement pst = null;
		try {
			conn = getConnection();
			
			
			pst = conn.prepareStatement(sql);
			
            //ResultSet rs = st.executeQuery("SELECT VERSION()");
			
			
            pst.setString(1, firstname);
            pst.setString(2, lastname);
            pst.setString(3, username);
            pst.setString(4, password);
            pst.setString(5, email);
            pst.setString(6, address);
            pst.setInt(7, dob);
            pst.setString(8, creditCard);
            
            pst.setBoolean(9, false);
            pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Unable to insert user. SQLException: " + e);
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
	}
}
