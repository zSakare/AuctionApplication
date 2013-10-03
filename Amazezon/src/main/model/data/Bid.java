package main.model.data;

import java.util.Date;

/**
 * Bid bean to represent a single bid. 
 */
public class Bid {
	private int bidID;
	private Date bidTime;
	private float bidPrice;
	private User bidder;
}
