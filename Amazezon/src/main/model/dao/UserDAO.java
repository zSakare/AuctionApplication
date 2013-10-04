package main.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private boolean loggedIn;
	private boolean errorInForm;
	private boolean isAdmin;
	private boolean banned;
	private String messages;
	
	//positions of columns in database
	private static final int USERID = 1;
	private static final int FIRSTNAME = 2;
	private static final int LASTNAME = 3;
	private static final int USERNAME = 4;
	private static final int PASSWORD = 5;
	private static final int EMAIL = 6;
	private static final int ADDRESS = 7;
	private static final int DOB = 8;
	private static final int CREDITCARD = 9;
	private static final int CONFIRMED = 10;
	private static final int ISADMIN = 11;
	private static final int BANNED = 12;
	

	public UserDAO() {
		this.loggedIn = false;
		this.errorInForm = false;
	}

	public void setAttributes(String username, String password, String email,
			String firstName, String lastName, String address, int dob,
			String creditCard, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dob = dob;
		this.creditCard = creditCard;
		this.loggedIn = false;
		this.errorInForm = false;
		this.isAdmin = isAdmin;

	}

	public String getUsername() {
		
		return username;
	}

	public boolean getErrorInForm() {
		return this.errorInForm;
	}
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setErrorInForm(boolean errorInForm) {
		this.errorInForm = errorInForm;
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
	public void doInsert() throws SQLException {

		Connection conn = null;
		String sql = "INSERT INTO USERS (firstname,lastname,username,password,email,address,yearOfBirth,creditCard,confirmed, isAdmin) VALUES (?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);

			// ResultSet rs = st.executeQuery("SELECT VERSION()");

			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setString(3, username);
			pst.setString(4, password);
			pst.setString(5, email);
			pst.setString(6, address);
			pst.setInt(7, dob);
			pst.setString(8, creditCard);

			pst.setBoolean(9, false);
			pst.setBoolean(10, isAdmin);
			pst.executeUpdate();
			this.errorInForm = false;
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
				e.printStackTrace();
			}
		}
	}
	
	public Admin loginAsAdmin() {
		if (this.isAdmin == false) {
			return null;
		} else {
			Admin newAdmin = new Admin();
			newAdmin.login(this.username,this.password);
			return newAdmin;
		}
	}
	
	public boolean login(String username, String password) {
		
		this.loggedIn = false;
		Connection conn = null;

		ResultSet rs = null;
		String sql = "SELECT * FROM USERS WHERE username=?"; //get the row with the username on it

		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			

			rs = pst.executeQuery();
			// extract data from the ResultSet
			// TODO: check if there are multiple results for a username
			if (rs == null) {
				// redirect to incorrect password page
			} else {
				String correctPass = null;
				String firstNameDB = null;
				String lastNameDB = null;
				String emailDB = null;
				String addressDB = null;
				int dobDB = 0;
				String creditCardDB = null;
				boolean isAdminDB = false;
				boolean bannedDB = false;
				//this loop should happen once
				while (rs.next()) { //retrieving user information from db 
					correctPass = rs.getString(PASSWORD);
					firstNameDB = rs.getString(FIRSTNAME);
					lastNameDB = rs.getString(LASTNAME);
					emailDB = rs.getString(EMAIL);
					addressDB = rs.getString(ADDRESS);
					dobDB = rs.getInt(DOB);
					creditCardDB = rs.getString(CREDITCARD);
					isAdminDB = rs.getBoolean(ISADMIN);
					bannedDB = rs.getBoolean(BANNED);
					
				}
				if (correctPass != null && correctPass.equals(password)) { //store the information about the user for the db into this object
					
					this.loggedIn = true;
					this.errorInForm = false;
					this.username=username;
					this.password=password;
					this.firstName=firstNameDB;
					this.lastName=lastNameDB;
					this.email = emailDB;
					this.address = addressDB;
					this.dob = dobDB;
					this.creditCard = creditCardDB;
					this.isAdmin = isAdminDB;
					this.banned = bannedDB;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.loggedIn;
	}
	
	
	public void logOut() {
		this.username = "";
		this.password = "";
		this.email = "";
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.dob = -1;
		this.creditCard = "";
		this.loggedIn = false;
		this.errorInForm = false;
	}
	
	//the following 2 functions are only here for the purpose of the userBean (easier to say userBean.isLoggedIn)
	public boolean getLoggedIn() {
		return this.loggedIn;
	}
	public String getMessages() {
		return this.messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
		
	}

	public void setConfirmed(String username) throws SQLException {
		Connection conn = null;
		String sql = "UPDATE Users SET confirmed=? WHERE username=?";

		PreparedStatement pst = null;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			pst.setBoolean(1, true);
			pst.setString(2, username);
			
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
				e.printStackTrace();
			}
		}
	}

}
