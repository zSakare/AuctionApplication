package main.model;

import java.io.Serializable;
import java.util.Date;

public class BidDAO extends JDBCDriver implements Serializable {
	
	private static final long serialVersionUID = 3735833103832481762L;
	
	// Bid details
	private int auction;
	private Date bidTime;
	private float bidPrice;
	
	// Bid on Item:
	private int bidder;
	private int bidID;
	
	
}
