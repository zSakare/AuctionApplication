package main.form.validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.model.dao.JDBCDriver;

public class BidValidator extends JDBCDriver {
	public List<FormError> validateBid(int auctionID, int bidAmount) {
		System.out.println("validating bid");
		
		List<FormError> formErrors = new ArrayList<FormError>();
		
		Connection conn = null;

		ResultSet rs = null;

		String sql = "select max(bidprice) as maxBid, biddingincrement, auctionid from auctions join bids on auctions.auctionid=bids.auction where auctionid=? group by auctionid;";

		
		
		PreparedStatement pst = null;
		try {
			conn = getConnection();

			pst = conn.prepareStatement(sql);
			pst.setInt(1,auctionID);

			rs = pst.executeQuery();
			
			// extract data from the ResultSet
			
			if (rs.next()) { 
				int maxBid = rs.getInt("maxBid");
				int biddingIncrement = rs.getInt("biddingincrement");
				
				
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

		return formErrors;
	}
}
