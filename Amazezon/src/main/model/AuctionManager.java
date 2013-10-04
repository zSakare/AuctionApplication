package main.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.model.dao.AuctionDAO;
import main.model.data.Auction;

public class AuctionManager {
	private static final ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public static void shutDownAuction(Auction auction) {
		executor.execute(new AuctionTerminator(auction));
	}
	
	private static class AuctionTerminator implements Runnable {
		private int auctionID;
		private long closingTime;
		
		public AuctionTerminator(Auction auction) {
			auctionID = auction.getAuctionID();
			closingTime = auction.getClosingTime().getTime();
		}

		@Override
		public void run() {
			try {
				Thread.sleep(closingTime);
				AuctionDAO auctionDAO = new AuctionDAO();
				
				auctionDAO.closeAuction(auctionID);
			} catch (InterruptedException e) {
				System.out.println("Failed to finish closing auction.");
			}
		}
	}
}
