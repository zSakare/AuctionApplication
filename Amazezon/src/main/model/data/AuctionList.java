package main.model.data;

import java.io.Serializable;
import java.util.List;

public class AuctionList implements Serializable {
	private static final long serialVersionUID = -346400208426960246L;
	private List<Auction> auctions;

	public List<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}
}
