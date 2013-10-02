package main.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDAO extends JDBCDriver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2260096444858487398L;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private int dob;
	private String creditCard;
	
	public UserDAO() {
		
	}
	
	public void setAttributes(String username, 
			String password, 
			String email, 
			String firstName,
			String lastName, 
			String address,
			int dob, 
			String creditCard) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.creditCard = creditCard;
		
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public int getDob() {
		return dob;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDob(int dob) {
		this.dob = dob;
	}

	public void setCreditCard(String creditCard) {
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
			
			
            pst.setString(1, firstName);
            pst.setString(2, lastName);
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
