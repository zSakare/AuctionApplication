package main.form.validator;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import main.controller.MailSender;
import main.model.dao.JDBCDriver;

public class BidValidator extends JDBCDriver {
	public List<String> validateBid(int auctionID, float bidAmount, int bidder) {
		System.out.println("validating bid");
		
		List<String> formMessages = new ArrayList<String>();
		
		//String sql = "select bidder, bidid, auctionid, biddingincrement, maxBid from (select bidid, auctionid, biddingIncrement, max(bidprice) as maxBid from auctions join bids on auctions.auctionid=bids.auction where auctionid=? group by auctionid,bidid) as maxbidtable join user_has_bids on maxbidtable.bidid=user_has_bids.bid;";
		
		float maxBid = getHighestBid(auctionID);
		float biddingIncrement = getBiddingIncrement(auctionID);
		if (isAuctionClosed(auctionID)) {
			formMessages.add("Sorry, the auction has closed");
		} else {
		
			if (maxBid != 0) {
				int highBidder = getHighBidder(auctionID);
				System.out.println("there is a max bid");
				
				
				if (highBidder != bidder) {
					notifyMessage(highBidder);
				}
				
				if (bidAmount < maxBid+biddingIncrement) {
					formMessages.add("The bid is not high enough. Must be higher than "+(maxBid+biddingIncrement));
					System.out.println("not high enough");
				} else {
					
					
					String html = "Congratulations! You are the highest bidder";
					MailSender sender = MailSender.getMailSender();
						try {
							sender.sendMessage("rofllol@gmail.com", getEmail(bidder),
									"Congratulations from Amazezon", html);
						} catch (AddressException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					formMessages.add("Congratulations, you are the highest bidder!");
					
				}
			} else {
				System.out.println("no max bid");
				
				float startBid = getStartBid(auctionID);
				if (bidAmount < startBid+biddingIncrement) {
					formMessages.add("The bid is not high enough. Must be higher than "+(startBid+biddingIncrement));
					
				} else {
					String html = "Congratulations! You are the highest bidder";
					MailSender sender = MailSender.getMailSender();
							try {
								sender.sendMessage("rofllol@gmail.com", getEmail(bidder),
										"Congratulations from Amazezon", html);
							} catch (AddressException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					formMessages.add("Congratulations, you are the highest bidder!");
					
				}
			}
		}

		return formMessages;
	}
	
	private boolean isAuctionClosed(int id) {
		Connection conn = null;
		boolean closed = false;
		ResultSet rs = null;
		

		String sql = "select closed from auctions where auctionid=?;";

		
		
		PreparedStatement pst = null;

		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);

			rs = pst.executeQuery();
			if (rs.next()) {
				closed= rs.getBoolean("closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return closed;
	}
	
	private String getEmail(int id) {
		
		Connection conn = null;

		ResultSet rs = null;
		String email = "";

		String sql = "select email from users where userid=?;";

		
		
		PreparedStatement pst = null;

		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);

			rs = pst.executeQuery();
			if (rs.next()) {
				email=rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return email;
	}
	
	private float getHighestBid(int id) {
		
		Connection conn = null;

		ResultSet rs = null;
		
		float maxBid = -1;
		String sql = "select max(bidPrice) as maxBid from auctions join bids on bids.auction=auctions.auctionid where auctionid=?;";

		
		
		PreparedStatement pst = null;

		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);

			rs = pst.executeQuery();
			if (rs.next()) {
				
				maxBid = rs.getFloat("maxBid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maxBid;
	}
	
	private float getBiddingIncrement(int id) {
		
		Connection conn = null;
	
		ResultSet rs = null;
		
	
		String sql = "select biddingIncrement from auctions where auctionid=?;";
	
		float biddingIncrement = -1;
		
		PreparedStatement pst = null;
	
		try {
			conn = getConnection();
	
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
	
			rs = pst.executeQuery();
			if (rs.next()) {
				biddingIncrement = rs.getFloat("biddingIncrement");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return biddingIncrement;
	}
	
	private float getStartBid(int id) {
		
		Connection conn = null;
	
		ResultSet rs = null;
		float biddingStartPrice = -1;
	
		String sql = "select biddingStartPrice from auctions where auctionid=?;";
	
		
		
		PreparedStatement pst = null;
	
		try {
			conn = getConnection();
	
			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
	
			rs = pst.executeQuery();
			if (rs.next()) {
				biddingStartPrice = rs.getFloat("biddingStartPrice");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return biddingStartPrice;
	}
	
	private int getHighBidder(int id) {
		float bidAmount = getHighestBid(id);
		
		
		Connection conn = null;
	
		ResultSet rs = null;
		
	
		String sql = "select bidder from bids join user_has_bids on user_has_bids.bid=bids.bidid where bidprice=?;";
	
		int bidder = -1;
		
		PreparedStatement pst = null;
	
		try {
			conn = getConnection();
	
			pst = conn.prepareStatement(sql);
			pst.setFloat(1,bidAmount);
	
			rs = pst.executeQuery();
			if (rs.next()) {
				bidder = rs.getInt("bidder");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bidder;
	}

	private void notifyMessage(int bidder) {
		Connection conn = null;
		String sql = "UPDATE Users SET messages=? WHERE userid=?";
		String messages = "Sorry you've been outbid!";
		PreparedStatement pst = null;
		try {
			conn = getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, messages);
			pst.setInt(2, bidder);
			
			pst.executeUpdate();
		} catch (SQLException e) {
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

