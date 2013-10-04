package main.model.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.data.Auction;
import main.model.data.User;

public class AuctionDAO extends JDBCDriver implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4942650556201129332L;
	private int ownerID;
	private String ownerUsername;
	
	private String title;
	private String category;
	private String picture;
	private String description;
	private String postage;
	private float reservePrice;
	private float biddingStartPrice;
	private float biddingIncrement;
	private int endTime;

	public void setAttributes(String ownerUsername, String title, String category,
			String picture, String description, String postage,
			float reservePrice, float biddingStartPrice,
			float biddingIncrement, int endTime) {
		this.ownerUsername = ownerUsername;
		
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

		String sql = "INSERT INTO Auctions (creator,title,category,picture,description,postage,reservePrice,biddingStartPrice,biddingIncrement,endTime) VALUES (?,?,?,?,?,?,?,?,?,?);";

		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);

			// ResultSet rs = st.executeQuery("SELECT VERSION()");

			pst.setInt(1, ownerID);
			pst.setString(2, title);
			pst.setString(3, category);
			pst.setString(4, picture);
			pst.setString(5, description);
			pst.setString(6, postage);
			pst.setFloat(7, reservePrice);
			pst.setFloat(8, biddingStartPrice);
			pst.setFloat(9, biddingIncrement);
			pst.setInt(10, endTime);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Unable to insert auction. SQLException: " + e);
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

	public List<Auction> selectWith(String title, String category) {
		
		List<Auction> auctionsFound = new ArrayList<Auction>();
		
		Connection conn = null;

		ResultSet rs = null;

		String sql = "SELECT auctionid, userid, title, category, " +
				"picture, description, postage, reserveprice, biddingstartprice, " +
				"biddingincrement, endtime, firstname, lastname, username, password, " +
				"email, address, yearofbirth, creditcard, halted, closed " +
				"FROM Auctions JOIN Users ON Users.userID=Auctions.creator " +
				"WHERE title SIMILAR TO ? and category SIMILAR TO ?;"; //get the row with the username on it

		if (title.isEmpty()) {
			title = "%";
		}
		
		if (category.isEmpty()) {
			category = "%";
		}
		
		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, category);

			rs = pst.executeQuery();
			
			// extract data from the ResultSet
			// Convert to auction list.
			while (rs.next()) { 
				Auction auction = new Auction();
				
				User owner = new User();
				if (!rs.getBoolean("halted") && !rs.getBoolean("closed")) {
					owner.setUserID(rs.getInt("userid"));
					owner.setUsername(rs.getString("username"));
					owner.setFirstname(rs.getString("firstname"));
					owner.setLastname(rs.getString("lastname"));
					owner.setEmail(rs.getString("email"));
					owner.setDob(rs.getInt("yearofbirth"));
					owner.setCreditCard(rs.getString("creditcard"));
					owner.setAddress(rs.getString("address"));
					
					auction.setAuctionID(rs.getInt("auctionid"));
					auction.setOwner(owner);
					auction.setTitle(rs.getString("title"));
					auction.setCategory(rs.getString("category"));
					auction.setPicture(rs.getString("picture"));
					auction.setDescription(rs.getString("description"));
					auction.setPostageDetails(rs.getString("postage"));
					auction.setReservePrice(rs.getFloat("reserveprice"));
					auction.setStartingPrice(rs.getFloat("biddingstartprice"));
					auction.setBidIncrements(rs.getFloat("biddingincrement"));
					auction.setClosingTime(rs.getInt("endtime"));
					
					auctionsFound.add(auction);
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

		return auctionsFound;
	}
	
	public Auction getAuctionWithID(int id) {
		
		Auction auction = new Auction();
		
		Connection conn = null;

		ResultSet rs = null;

		String sql = "SELECT auctionid, userid, title, category, " +
				"picture, description, postage, reserveprice, biddingstartprice, " +
				"biddingincrement, endtime, firstname, lastname, username, password, " +
				"email, address, yearofbirth, creditcard, halted " +
				"FROM Auctions JOIN Users ON Users.userID=Auctions.creator " +
				"WHERE auctionid=?;";

		
		
		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			// extract data from the ResultSet
			// Convert to auction list.
			while (rs.next()) { 
				
				
				
				User owner = new User();
				if (!rs.getBoolean("halted")) {
					owner.setUserID(rs.getInt("userid"));
					owner.setUsername(rs.getString("username"));
					owner.setFirstname(rs.getString("firstname"));
					owner.setLastname(rs.getString("lastname"));
					owner.setEmail(rs.getString("email"));
					owner.setDob(rs.getInt("yearofbirth"));
					owner.setCreditCard(rs.getString("creditcard"));
					owner.setAddress(rs.getString("address"));
					
					auction.setAuctionID(rs.getInt("auctionid"));
					auction.setOwner(owner);
					auction.setTitle(rs.getString("title"));
					auction.setCategory(rs.getString("category"));
					auction.setPicture(rs.getString("picture")); 
					auction.setDescription(rs.getString("description"));
					auction.setPostageDetails(rs.getString("postage"));
					auction.setReservePrice(rs.getFloat("reserveprice"));
					auction.setStartingPrice(rs.getFloat("biddingstartprice"));
					auction.setBidIncrements(rs.getFloat("biddingincrement"));
					auction.setClosingTime(rs.getInt("endtime"));
					
					
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

		return auction;
	}
	
	public List<Auction> getAllAuctions() {
		List<Auction> auctionsFound = new ArrayList<Auction>();
		
		Connection conn = null;

		ResultSet rs = null;

		String sql = "SELECT auctionid, userid, title, category, " +
				"picture, description, postage, reserveprice, biddingstartprice, " +
				"biddingincrement, endtime, firstname, lastname, username, password, " +
				"email, address, yearofbirth, creditcard, halted, closed " +
				"FROM Auctions JOIN Users ON Users.userID=Auctions.creator;";

		
		
		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			

			rs = pst.executeQuery();
			
			// extract data from the ResultSet
			// Convert to auction list.
			while (rs.next()) { 
				System.out.println("in loop");
				Auction auction = new Auction();
				
				User owner = new User();
				if (!rs.getBoolean("halted") && !rs.getBoolean("closed")) {
					owner.setUserID(rs.getInt("userid"));
					owner.setUsername(rs.getString("username"));
					owner.setFirstname(rs.getString("firstname"));
					owner.setLastname(rs.getString("lastname"));
					owner.setEmail(rs.getString("email"));
					owner.setDob(rs.getInt("yearofbirth"));
					owner.setCreditCard(rs.getString("creditcard"));
					owner.setAddress(rs.getString("address"));
					
					auction.setAuctionID(rs.getInt("auctionid"));
					auction.setOwner(owner);
					auction.setTitle(rs.getString("title"));
					auction.setCategory(rs.getString("category"));
					auction.setPicture(rs.getString("picture")); 
					auction.setDescription(rs.getString("description"));
					auction.setPostageDetails(rs.getString("postage"));
					auction.setReservePrice(rs.getFloat("reserveprice"));
					auction.setStartingPrice(rs.getFloat("biddingstartprice"));
					auction.setBidIncrements(rs.getFloat("biddingincrement"));
					auction.setClosingTime(rs.getInt("endtime"));
					
					auctionsFound.add(auction);
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

		return auctionsFound;
	}

	public void closeAuction(int auctionID) {
		Connection conn = null;
		String sql = "UPDATE Auctions SET closed=? WHERE auctionid=?";

		PreparedStatement pst = null;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setBoolean(1, true);
			pst.setInt(2, auctionID);
			
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
		}
	}

	public Integer getAuctionID() {
		Integer auctionid = null;
		Connection conn = null;
		String sql = "SELECT auctionid FROM auctions WHERE title=? and category=? and picture=? and endTime=?";

		PreparedStatement pst = null;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, title);
			pst.setString(2, category);
			pst.setString(3, picture);
			pst.setInt(4, endTime);
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				auctionid = rs.getInt("auctionid");
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
		return auctionid;
	}
}
