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

import main.form.RegistrationForm;
import main.form.validator.RegistrationFormValidator;
import main.model.dao.AuctionDAO;
import main.model.dao.UserDAO;

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
	private static final String LOGIN = "login";
	private static final String ACTION = "action";
	private static final String AUCTION = "createAuction";
	
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
			RegistrationForm form = (RegistrationForm) request.getAttribute("form");
			
			if (form == null) {
				form = new RegistrationForm();
			}
			
			form.setUsername(request.getParameter("username"));
			form.setFirstname(request.getParameter("firstName"));
			form.setLastname(request.getParameter("lastName"));
			form.setPassword(request.getParameter("password"));
			form.setPasswordConfirm(request.getParameter("passwordConfirm"));
			form.setEmail(request.getParameter("email"));
			form.setAddress(request.getParameter("address"));
			form.setDob(Integer.parseInt(request.getParameter("dob")));
			form.setCreditCard(request.getParameter("creditCard"));
			
			if (form.getUsername().isEmpty() || form.getPassword().isEmpty() || form.getCreditCard().isEmpty() || form.getEmail().isEmpty()) {
				System.out.println("A field that cannot be null is missing a value.");
				request.getSession().setAttribute("form", form);
				response.sendRedirect("register.jsp");
			} else if (!RegistrationFormValidator.validate(form).isEmpty()) {
				// There are errors, put them in the register page and resend data.
				request.getSession().setAttribute("form", form);
				response.sendRedirect("register.jsp");
				return;
			} else {
				
				//request.getSession().setAttribute("newUser", newUser);
				UserDAO userBean = (UserDAO) request.getSession().getAttribute("userBean"); // get the bean the user created
				userBean.setAttributes(form.getUsername(), 
										form.getPassword(), 
										form.getEmail(), 
										form.getFirstname(), 
										form.getLastname(), 
										form.getAddress(), 
										form.getDob(), 
										form.getCreditCard()); //same as what the constructor did before
				
				System.out.println("first name: "+ userBean.getFirstName());
				
				try {
					userBean.doInsert();
					
					response.sendRedirect("admin.jsp");
				} catch (Exception e) {
					// TODO: send user to error page.
					e.printStackTrace();
					response.sendRedirect("error.jsp");
				}
				
			}
			//the following is only needed if we are not using a session bean
			//request.getSession().setAttribute("userBean", userBean); //send the bean through for the next page
			
		} else if (LOGIN.equals(request.getParameter(ACTION))) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserDAO userBean = (UserDAO) request.getSession().getAttribute("userBean"); // get the bean the user created
			
			System.out.println("loginstatus is: "+request.getSession().getAttribute("loginStatus"));
			if (userBean.login(username, password)) {
				response.sendRedirect("admin.jsp");
			} else {
				request.getSession().setAttribute("loginStatus","failed");
				response.sendRedirect("login.jsp");
			}
		} else if (AUCTION.equals(request.getParameter(ACTION))) {
			UserDAO userBean = (UserDAO) request.getSession().getAttribute("userBean"); // get the bean the user created
			String username = userBean.getUsername();
			String itemName = request.getParameter("itemName");
			String title = request.getParameter("title");
			String category = request.getParameter("category");
			String picture = request.getParameter("picture");
			String description = request.getParameter("description");
			String postageDetails = request.getParameter("postageDetails");
			Float reservePrice = Float.parseFloat(request.getParameter("reservePrice"));
			Float biddingStartPrice = Float.parseFloat(request.getParameter("biddingStartPrice"));
			Float biddingIncrement = Float.parseFloat(request.getParameter("biddingIncrements"));
			int endTime = Integer.parseInt(request.getParameter("endTime"));
			AuctionDAO auctionDAO = new AuctionDAO();
			auctionDAO.setAttributes(username, itemName, title, category, picture, description, postageDetails, reservePrice, biddingStartPrice, biddingIncrement, endTime);
			try {
				auctionDAO.doInsert();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

