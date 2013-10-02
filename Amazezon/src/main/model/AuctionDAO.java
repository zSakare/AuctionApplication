package main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuctionDAO {
	public static void main (String[] args) {
		System.out.println("starting query");
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5923/AuctionDB","auctionadmin","admin");
			String sqlStatement = "INSERT INTO USERS (firstname,lastname,username,password,email,address,yearOfBirth,creditCard,confirmed) VALUES (?,?,?,?,?,?,?,?,?);";
			//String sqlStatement = "INSERT INTO USERS (firstname,lastname,username,password,email,address,yearOfBirth,creditCard,confirmed) VALUES ('daniel','morton','daniel','password','email','address','dob','creditCard','false');";
			PreparedStatement pst = conn.prepareStatement(sqlStatement);
			
            //ResultSet rs = st.executeQuery("SELECT VERSION()");
            pst = conn.prepareStatement(sqlStatement);
            pst.setString(1, "daniel");
            pst.setString(2, "morton");
            pst.setString(3, "daniel");
            pst.setString(4, "password");
            pst.setString(5, "email");
            pst.setString(6, "address");
            pst.setInt(7, 1);
            pst.setString(8, "fuckoff");
            
            pst.setBoolean(9, false);
            pst.executeUpdate();

            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("query succeeded!");

		
	}
}
