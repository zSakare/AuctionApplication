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
			
			this.setMessages("Banned "+user+"!");
		}
		
	}
	public void haltAuction(String auction) {
		
	}
	public void removeAuction(String auction) {
		
	}
}
