package main.form;

public class NewAuctionForm {

	private String title;
	private String category;
	private String picture;
	private String description;
	private String postageDetails;
	private Float reservePrice;
	private Float biddingStartPrice;
	private Float biddingIncrement;
	private int endTime;
	
	public String getTitle() {
		return title;
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

	public String getPicture() {
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

	public Float getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(Float reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Float getBiddingStartPrice() {
		return biddingStartPrice;
	}

	public void setBiddingStartPrice(Float biddingStartPrice) {
		this.biddingStartPrice = biddingStartPrice;
	}

	public Float getBiddingIncrement() {
		return biddingIncrement;
	}

	public void setBiddingIncrement(Float biddingIncrement) {
		this.biddingIncrement = biddingIncrement;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}


}
