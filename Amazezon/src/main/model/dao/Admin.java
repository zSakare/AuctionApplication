package main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends UserDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2406643594940433765L;
	
	public Admin () {
		super();
	}
	
	public void ban(String user) {
		String sql = "UPDATE USERS SET banned=true WHERE username=?;";
		boolean success = true;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);

			// ResultSet rs = st.executeQuery("SELECT VERSION()");

			pst.setString(1, user);
			 
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
