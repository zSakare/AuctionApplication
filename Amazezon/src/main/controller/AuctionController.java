package main.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import main.model.UserDAO;

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
	private static final String REGISTER = "register";
	private static final String ACTION = "action";
	
	final Logger logger = Logger.getLogger(this.getClass().getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionController() {

    }
    
    public void init() {
    	// Test code.
    	try {
			InitialContext cxt = new InitialContext();
			if ( cxt == null ) {
			   throw new Exception("Uh oh -- no context!");
			}
			Connection conn;
			DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/postgres" );
			
			if ( ds == null ) {
			   throw new Exception("Data source not found!");
			}
	    	
	    	if (ds != null && cxt != null) {
	    		System.out.println("FAWK YEAH");
	    		
	    		try {
	                Class.forName("org.postgresql.Driver");



	            }
	            catch (java.lang.ClassNotFoundException e) {
	                java.lang.System.err.print("ClassNotFoundException: Postgres Server JDBC");
	                java.lang.System.err.println(e.getMessage());
	                throw new Exception("No JDBC Driver found in Server");
	            }
	    		
	    		try {
	    			
	    			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5923/AuctionDB","auctionadmin","admin");
	    			
	    			System.out.println("Connected");
	    		} catch (SQLException E) {

	                java.lang.System.out.println("SQLException: " + E.getMessage());
	                java.lang.System.out.println("SQLState: " + E.getSQLState());
	                java.lang.System.out.println("VendorError: " + E.getErrorCode());

	            }
	    		
	    	}
    	} catch (Exception e) {
    		System.err.println("Problem establishing connection: " + e);
    	}
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
		if (REGISTER.equals(request.getParameter(ACTION))) {
			String username = request.getParameter("username");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String dob = request.getParameter("dob");
			String creditCard = request.getParameter("creditCard");
			
			if (username.isEmpty() || password.isEmpty() || creditCard.isEmpty() || email.isEmpty()) {
				System.out.println("A field that cannot be null is missing a value.");
			} else {
				UserDAO newUser = new UserDAO(username, password, email, firstname, lastname, address, dob, creditCard);
				try {
					newUser.doInsert();
				} catch (Exception e) {
					// TODO: send user to error page.
					e.printStackTrace();
				}
			}
			
			response.sendRedirect("index.jsp");
		}
	}
}

