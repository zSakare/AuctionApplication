package main.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Auction Controller:
 * Handles -
 * 	1. User Registration - will need register.jsp (registration form), confirm.jsp (confirmation page)
 * 	2. Offering Items for Auction - new-auction.jsp (auction creation), auction.jsp (auction list)
 * 	3. Bidding - auction.jsp (auction list), bid.jsp (bid form).
 *	4. Admin Functionality - admin.jsp (administration page).
 */
@WebServlet(name="AuctionController",urlPatterns={"/AuctionController","/","/home"})
public class AuctionController extends HttpServlet {
	private static final long serialVersionUID = -4203748354523622984L;
	final Logger logger = Logger.getLogger(this.getClass().getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionController() {
    	
    }
    
    public void init(){
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}

