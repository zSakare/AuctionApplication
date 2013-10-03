package main.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuctionDAO extends JDBCDriver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4942650556201129332L;
	private int ownerID;
	private String ownerUsername;
	private String itemName;
	private String title;
	private String category;
	private String picture;
	private String description;
	private String postage;
	private float reservePrice;
	private float biddingStartPrice;
	private float biddingIncrement;
	private int endTime;

	public void setAttributes(String ownerUsername, String itemName, String title, String category,
			String picture, String description, String postage,
			float reservePrice, float biddingStartPrice,
			float biddingIncrement, int endTime) {
		this.ownerUsername = ownerUsername;
		this.itemName = itemName;
		this.title = title;
		this.category = category;
		this.picture = picture;
		this.description = description;
		this.postage = postage;
		this.reservePrice = reservePrice;
		this.biddingStartPrice = biddingStartPrice;
		this.biddingIncrement = biddingIncrement;
		this.endTime = endTime;

	}

	
	
	
	@Override
	public void doInsert() throws Exception {

		Connection conn = null;
		
		loadOwnerIDFromDB();

		String sql = "INSERT INTO Auctions (creator,itemName,title,category,picture,description,postage,reservePrice,biddingStartPrice,biddingIncrement,endTime) VALUES (?,?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);

			// ResultSet rs = st.executeQuery("SELECT VERSION()");

			pst.setInt(1, ownerID);
			pst.setString(2, itemName);
			pst.setString(3, title);
			pst.setString(4, category);
			pst.setString(5, picture);
			pst.setString(6, description);
			pst.setString(7, postage);
			pst.setFloat(8, reservePrice);
			pst.setFloat(9, biddingStartPrice);
			pst.setFloat(10, biddingIncrement);
			pst.setInt(11, endTime);
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
	
	public String getOwnerUsername() {
		return ownerUsername;
	}
	
	public String getItemName() {
		return itemName;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public String getPicture() {
		return picture;
	}

	public String getDescription() {
		return description;
	}

	public String getPostage() {
		return postage;
	}

	public float getReservePrice() {
		return reservePrice;
	}

	public float getBiddingStartPrice() {
		return biddingStartPrice;
	}

	public float getBiddingIncrement() {
		return biddingIncrement;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public void setReservePrice(float reservePrice) {
		this.reservePrice = reservePrice;
	}

	public void setBiddingStartPrice(float biddingStartPrice) {
		this.biddingStartPrice = biddingStartPrice;
	}

	public void setBiddingIncrement(float biddingIncrement) {
		this.biddingIncrement = biddingIncrement;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
	public void loadOwnerIDFromDB() {
		Connection conn = null;

		ResultSet rs = null;
		String sql = "SELECT userID FROM USERS WHERE username=?"; //get the row with the username on it

		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setString(1, ownerUsername);
			

			rs = pst.executeQuery();
			// extract data from the ResultSet
			// TODO: check if there are multiple results for a username
			
			//this loop should happen once
			while (rs.next()) { //retrieving user information from db 
				
				ownerID = rs.getInt(1);
				
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
	
}
