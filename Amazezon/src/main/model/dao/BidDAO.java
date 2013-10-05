package main.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BidDAO extends JDBCDriver implements Serializable {
	
	private static final long serialVersionUID = 3735833103832481762L;
	private static final int BIDID = 1;
	private static final int AUCTION = 2;
	private static final int BIDTIME = 3;
	private static final int BIDPRICE = 4;
	
	// Bid details
	private int auction;
	private Date bidTime;
	private float bidPrice;
	private int bidder;

	public void setAttributes(int auction, Date bidTime, float bidPrice, int bidder) {
		this.auction = auction;
		this.bidTime = bidTime;
		this.bidPrice = bidPrice;
		this.bidder = bidder;
	}

	@Override
	public void doInsert() throws SQLException {
		Connection conn = null;

		String sql1 = "INSERT INTO Bids (auction,bidTime,bidPrice) VALUES (?,?,?);";
		String sql2 = "INSERT INTO User_has_Bids (bidder,bid) VALUES (?,?);";
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		
		try {
			conn = getConnection();

			pst1 = conn.prepareStatement(sql1);
			pst2 = conn.prepareStatement(sql2);
			
			
			pst1.setInt(1, auction);
			pst1.setLong(2, bidTime.getTime());
			pst1.setFloat(3, bidPrice);
			pst2.setInt(1, bidder);
			
			
			
			pst1.executeUpdate();
			
			String sql3 = "SELECT LASTVAL()";
			
			pst3 = conn.prepareStatement(sql3);
			
			ResultSet rs = pst3.executeQuery();
			int bidID = -1;
			if (rs.next()) {
				bidID = rs.getInt(1);
			}
			
			

			pst2.setFloat(2,bidID);
			
			pst2.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Unable to insert bid. SQLException: " + e);
		} finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				
				if (pst2 != null) {
					pst2.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public int getAuction() {
		return auction;
	}

	public void setAuction(int auction) {
		this.auction = auction;
	}

	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}

	public float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}
}
