package main.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public void setAttributes(int auction, Date bidTime, float bidPrice) {
		this.auction = auction;
		this.bidTime = bidTime;
		this.bidPrice = bidPrice;
	}

	@Override
	public void doInsert() throws SQLException {
		Connection conn = null;

		String sql = "INSERT INTO USERS (auction,bidTime,bidPrice) VALUES (?,?,?);";

		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);

			pst.setInt(1, auction);
			pst.setLong(2, bidTime.getTime());
			pst.setFloat(3, bidPrice);
			
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
