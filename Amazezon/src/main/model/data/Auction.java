package main.model.data;

import java.util.Date;
import java.util.List;

/**
 * Auction bean to describe an auction.
 */
public class Auction {
	private int auctionID;
	private User owner;
	private String itemName;
	private byte[] picture;
	private String description;
	private String postageDetails;
	private float reservePrice;
	private float startingPrice;
	private float bidIncrements;
	private Date closingTime;
	private List<Bid> bids;
	
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
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
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
	public Date getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}
	public List<Bid> getBids() {
		return bids;
	}
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
}
