package main.form.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.controller.MailSender;
import main.model.dao.JDBCDriver;

public class BidValidator extends JDBCDriver {
	public List<String> validateBid(int auctionID, float bidAmount, int bidder) {
		System.out.println("validating bid");
		
		List<String> formMessages = new ArrayList<String>();
		
		Connection conn = null;

		ResultSet rs = null;
		ResultSet rs2 = null;

		String sql = "select bidder, bidid, auctionid, biddingincrement, maxBid from (select bidid, auctionid, biddingIncrement, max(bidprice) as maxBid from auctions join bids on auctions.auctionid=bids.auction where auctionid=? group by auctionid,bidid) as maxbidtable join user_has_bids on maxbidtable.bidid=user_has_bids.bid;";

		
		
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setInt(1,auctionID);

			rs = pst.executeQuery();
			
			// extract data from the ResultSet
			
			if (rs.next()) { 
				System.out.println("there is a max bid");
				int maxBid = rs.getInt("maxBid");
				int biddingIncrement = rs.getInt("biddingincrement");
				int previousHighBidder = rs.getInt("bidder");
				if (previousHighBidder != bidder) {
					notifyMessage(previousHighBidder);
				}
				
				if (bidAmount < maxBid+biddingIncrement) {
					formMessages.add("The bid is not high enough");
					System.out.println("not high enough");
				} else {
					
					
					String html = "Congratulations! You are the highest bidder";
					MailSender sender = MailSender.getMailSender();
					sender.sendMessage("rofllol@gmail.com", getEmail(bidder),
							"Congratulations from Amazezon", html);
					formMessages.add("Congratulations, you are the highest bidder!");
					
				}
				
			} else {
				System.out.println("no max bid");
				String sql1 = "Select biddingIncrement,biddingStartPrice from auctions where auctionid=?;";
				pst2 = conn.prepareStatement(sql1);
				pst2.setInt(1,auctionID);
				System.out.println("auction id is "+auctionID);
				rs2 = pst2.executeQuery();
				
				if (rs2.next()) {
					int startBid = rs2.getInt("biddingStartPrice");
					int biddingIncrement = rs2.getInt("biddingIncrement");
					if (bidAmount < startBid+biddingIncrement) {
						formMessages.add("The bid is not high enough");
						
					} else {
						String html = "Congratulations! You are the highest bidder";
						MailSender sender = MailSender.getMailSender();
						sender.sendMessage("rofllol@gmail.com", getEmail(bidder),
								"Congratulations from Amazezon", html);
						formMessages.add("Congratulations, you are the highest bidder!");
						
					}
					
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

		return formMessages;
	}
	
	private String getEmail(int id) {
		
		Connection conn = null;

		ResultSet rs = null;
		

		String sql = "select email from users where userid=?;";

		
		
		PreparedStatement pst = null;

		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setInt(1,id);

			rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	private void notifyMessage(int bidder) {
		
	}
}

