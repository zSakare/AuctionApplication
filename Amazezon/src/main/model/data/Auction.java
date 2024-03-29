package main.model.data;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import main.model.dao.JDBCDriver;

/**
 * Auction bean to describe an auction.
 */
public class Auction extends JDBCDriver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7097444102553414751L;
	private int auctionID;
	private User owner;
	private String title;
	private String picture;
	private String description;
	private String postageDetails;
	private float reservePrice;
	private float startingPrice;
	private float bidIncrements;
	private int closingTime;
	private List<Bid> bids;
	private String category;
	private boolean closed;
	private Date startTime;
	
	
	// Getters and setters:
	public int getAuctionID() {
		return auctionID;
	}
	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}
	public User getOwner() {
		return owner;
	}
	
	public float getHighestBid() {
		Connection conn = null;

		ResultSet rs = null;
		String sql = "select auction, max(bidprice) as maxBidPrice from bids where auction=? group by auction;";
		float highest = startingPrice;
		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setInt(1, auctionID);
			

			rs = pst.executeQuery();
			// extract data from the ResultSet
			if (rs.next()) {
				highest = rs.getFloat("maxBidPrice");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				
				if (pst != null) {
					pst.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return highest;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getPicture() {
		System.out.println("get picture was called");
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPostageDetails() {
		return postageDetails;
	}
	public void setPostageDetails(String postageDetails) {
		this.postageDetails = postageDetails;
	}
	public float getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(float reservePrice) {
		this.reservePrice = reservePrice;
	}
	public float getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(float startingPrice) {
		this.startingPrice = startingPrice;
	}
	public float getBidIncrements() {
		return bidIncrements;
	}
	public void setBidIncrements(float bidIncrements) {
		this.bidIncrements = bidIncrements;
	}
	public int getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(int closingTime) {
		this.closingTime = closingTime;
	}
	public List<Bid> getBids() {
		return bids;
	}
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean getClosed() {
		System.out.println("this was called");
		if (!closed) {
			Connection conn = null;

			ResultSet rs = null;

			String sql = "SELECT closed FROM Auctions WHERE auctionid=?;";

			
			
			PreparedStatement pst = null;
			try {
				conn = getConnection();

				pst = conn.prepareStatement(sql);
				pst.setInt(1, auctionID);

				rs = pst.executeQuery();
				
				// extract data from the ResultSet
				// Convert to auction list.
				if (rs.next()) { 
					this.closed = rs.getBoolean("closed");
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
		}
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	public Date getStartTime() {
		return startTime;
	}
	
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return new Date(this.startTime.getTime() + (this.closingTime*1000*60));
	}
}
