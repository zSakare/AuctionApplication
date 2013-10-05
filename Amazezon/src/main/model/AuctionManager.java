package main.model;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import main.controller.MailSender;
import main.model.dao.AuctionDAO;
import main.model.dao.BidDAO;
import main.model.dao.UserDAO;
import main.model.data.Auction;

public class AuctionManager {
	private static final ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public static void shutDownAuction(Auction auction) {
		executor.execute(new AuctionTerminator(auction));
	}
	
	private static class AuctionTerminator implements Runnable {
		private int auctionID;
		private int closingTime;
		
		public AuctionTerminator(Auction auction) {
			auctionID = auction.getAuctionID();
			closingTime = auction.getClosingTime();
		}

		@Override
		public void run() {
			try {
				Thread.sleep(closingTime*1000*60); // convert to milliseconds
				AuctionDAO auctionDAO = new AuctionDAO();
				auctionDAO.closeAuction(auctionID);
				
				BidDAO bidDAO = new BidDAO();
				Float bidPrice = bidDAO.getHighestBid(auctionID);
				Integer userID = bidDAO.getHighestBidder(auctionID);
				
				if (bidPrice != null) {
					UserDAO userDAO = new UserDAO();
					String email = userDAO.findUserEmail(userID);
					Auction auction = auctionDAO.getAuctionWithID(auctionID);
					
					if (bidPrice >= auction.getReservePrice()) {
						MailSender sender = MailSender.getMailSender();
						String html = "Success! You have won the auction for: " + auction.getTitle() + ".";
						sender.sendMessage("rofllol@gmail.com", email,
								"You won!", html);
						html = "Your auction for: " + auction.getTitle() + " has finished.";
						sender.sendMessage("rofllol@gmail.com", auction.getOwner().getEmail(),
								"Auction finished", html);
					}
				}
			} catch (InterruptedException e) {
				System.out.println("Failed to finish closing auction.");
			} catch (SQLException e) {
				System.out.println("Failed to find max bidder.");
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
}
