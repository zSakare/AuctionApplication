package main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.model.data.Auction;
import main.model.data.User;

public class Admin extends UserDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2406643594940433765L;
	
	public Admin () {
		super();
	}
	
	public int getUserIdFromUsername(String user) {
		int userID = -1;
		Connection conn = null;

		ResultSet rs = null;

		String sql = "SELECT userid FROM users where username=?";

		
		
		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			
			pst.setString(1, user);
			rs = pst.executeQuery();
			
			// extract data from the ResultSet
			while (rs.next()) { 
				
				userID = rs.getInt("userid");
				
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

		return userID;
	}
	
	public void ban(String user) {
		int userID = getUserIdFromUsername(user);
		String sql = "UPDATE USERS SET banned=true WHERE userID=?;";
		String haltAuctions = "UPDATE AUCTIONS SET halted=true where creator=?;";
		boolean success = true;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		Connection conn = null;
		try {
			conn = getConnection();

			pst1 = conn.prepareStatement(sql);
			pst2 = conn.prepareStatement(haltAuctions);


			pst1.setInt(1, userID);
			pst2.setInt(1, userID);
			pst1.executeUpdate();
			pst2.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		} finally {
			try {
				if (pst1 != null ) {
					pst1.close();
				}
				if (pst2 != null ) {
					pst2.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
			}
			if (success) {
				this.setMessages("Banned "+user+"!");
			} else {
				this.setMessages("Something went wrong");
			}
		}
		
	}
	public void haltAuction(int auction) {
		String sql = "UPDATE auctions SET halted=true WHERE auctionID=?;";
		boolean success = true;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);

			// ResultSet rs = st.executeQuery("SELECT VERSION()");

			pst.setInt(1, auction);
			 
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
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
				success = false;
			}
			if (success) {
				this.setMessages("Halted "+auction+"!");
			} else {
				this.setMessages("Something went wrong");
			}
		}
	}
	
	
	
	public void removeAuction(int auction) {
		String sql = "DELETE FROM auctions WHERE auctionID=?;";
		boolean success = true;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);

			// ResultSet rs = st.executeQuery("SELECT VERSION()");

			pst.setInt(1, auction);
			 
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
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
				success = false;
			}
			if (success) {
				this.setMessages("Removed auction "+auction+"!");
			} else {
				this.setMessages("Something went wrong");
			}
		}
		
	}
}
