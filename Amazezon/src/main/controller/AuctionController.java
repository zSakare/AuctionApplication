package main.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.form.NewAuctionForm;
import main.form.RegistrationForm;
import main.form.SearchForm;
import main.form.validator.NewAuctionFormValidator;
import main.form.validator.RegistrationFormValidator;
import main.model.AuctionManager;
import main.model.dao.Admin;
import main.model.dao.AuctionDAO;
import main.model.dao.UserDAO;
import main.model.data.Auction;
import main.model.data.AuctionList;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

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
	private static final String AUCTION_SEARCH = "auctionSearch";
	private static final String FORM = "form";
	private static final long serialVersionUID = -4203748354523622984L;
	private static final String REGISTER = "register";
	private static final String LOGIN = "login";
	private static final String ACTION = "action";
	private static final String AUCTION = "createAuction";
	private static final String BAN = "ban";
	private static final String REMOVEAUCTION = "removeAuction";
	private static final String HALTAUCTION = "haltAuction";
	private static final String VIEWAUCTION = "viewAuction";
	
	final Logger logger = Logger.getLogger(this.getClass().getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionController() {

    }
    
    public void init() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AUCTION_SEARCH.equals(request.getParameter(ACTION))) {
			SearchForm form = (SearchForm) request.getSession().getAttribute("searchForm");
			
			if (form == null) {
				form = new SearchForm();
			}
			
			form.setTitle(request.getParameter("title"));
			form.setCategory(request.getParameter("category"));
			
			List<Auction> auctionsFound = searchForAuctions(form.getTitle(), form.getCategory());
			AuctionList auctions = new AuctionList(); 
			
			auctions.setAuctions(auctionsFound);
			
			request.getSession().setAttribute("auctionList", auctions);
			request.getSession().setAttribute("searchForm", form);
			response.sendRedirect("auction.jsp");
		} else {
			StringBuffer sb = request.getRequestURL();
			String username = sb.substring(sb.indexOf("=")+1);
			
			UserDAO userDAO = new UserDAO();
			try {
				userDAO.setConfirmed(username);
				request.getSession().setAttribute("success", "true");
				response.sendRedirect("confirm.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect("error.jsp");
			}
		}
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (REGISTER.equals(request.getParameter(ACTION))) {
			RegistrationForm form = setRegistrationForm(request);
			
			if (form.getUsername().isEmpty() || form.getPassword().isEmpty() || form.getCreditCard().isEmpty() || form.getEmail().isEmpty()) {
				System.out.println("A field that cannot be null is missing a value.");
				request.getSession().setAttribute(FORM, form);
				response.sendRedirect("register.jsp");
			} else if (!RegistrationFormValidator.validate(form).isEmpty()) {
				// There are errors, put them in the register page and resend data.
				request.getSession().setAttribute(FORM, form);
				response.sendRedirect("register.jsp");
				return;
			} else {
				UserDAO userBean = (UserDAO) request.getSession().getAttribute("userBean"); // get the bean the user created
				
				
				
				userBean.setAttributes(form.getUsername(), 
										form.getPassword(), 
										form.getEmail(), 
										form.getFirstname(), 
										form.getLastname(), 
										form.getAddress(), 
										form.getDob(), 
										form.getCreditCard(),
										form.getIsAdmin()); //same as what the constructor did before
				
				System.out.println("first name: "+ userBean.getFirstName());
				
				try {
					userBean.doInsert();
					userBean.login(form.getUsername(), form.getPassword());
					
					String html = "Welcome to Amazezon click " +
							"<a href='http://localhost:8080/Amazezon/username=" + form.getUsername() + 
							"'>here</a> to confirm your registration with us.";
					MailSender sender = MailSender.getMailSender();
					sender.sendMessage("rofllol@gmail.com", form.getEmail(), "Confirmation for Amazezon", html);
					
					if (form.getIsAdmin()) {
						response.sendRedirect("admin.jsp");
					} else {
						response.sendRedirect("auction.jsp");
					}
				} catch (Exception e) {
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
				if (userBean.getIsAdmin()) {
					response.sendRedirect("admin.jsp");
				} else {
					response.sendRedirect("auction.jsp");
				}
			} else {
				request.getSession().setAttribute("loginStatus","failed");
				response.sendRedirect("login.jsp");
			}
		} else if (BAN.equals(request.getParameter(ACTION))) {
			Admin userBean = ((UserDAO) request.getSession().getAttribute("userBean")).loginAsAdmin(); // get the bean the user created
			
			String userToBan = request.getParameter("username");
			if (userBean != null) { //if it is not null, it has successfully been logged in as admin 
				userBean.ban(userToBan);
			}
			request.getSession().setAttribute("userBean", (UserDAO)userBean);
			response.sendRedirect("admin.jsp");
		} else if (HALTAUCTION.equals(request.getParameter(ACTION))) {
			Admin userBean = ((UserDAO) request.getSession().getAttribute("userBean")).loginAsAdmin(); // get the bean the user created
			
			try {
				int auctionid = Integer.parseInt(request.getParameter("auctionid"));
				if (userBean != null) { //if it is not null, it has successfully been logged in as admin 
					userBean.haltAuction(auctionid);
				}
			} catch (Exception e) {
				userBean.setMessages(request.getParameter("auctionid")+" is not a valid id!");
			}
			
			request.getSession().setAttribute("userBean", (UserDAO)userBean);
			response.sendRedirect("admin.jsp");
		} else if (REMOVEAUCTION.equals(request.getParameter(ACTION))) {
			Admin userBean = ((UserDAO) request.getSession().getAttribute("userBean")).loginAsAdmin(); // get the bean the user created
			
			
			try {
				int auctionid = Integer.parseInt(request.getParameter("auctionid"));
				if (userBean != null) { //if it is not null, it has successfully been logged in as admin 
					userBean.removeAuction(auctionid);
				}
			} catch (Exception e) {
				userBean.setMessages(request.getParameter("auctionid")+" is not a valid id!");
			}
			
			request.getSession().setAttribute("userBean", (UserDAO)userBean);
			
			response.sendRedirect("admin.jsp");
		} else if (VIEWAUCTION.equals(request.getParameter(ACTION))) { 
			String id = request.getParameter("auctionID");
			AuctionDAO auctionDAO = new AuctionDAO();
			
			try {
				Auction auctionBean = auctionDAO.getAuctionWithID(Integer.parseInt(id));
				request.getSession().setAttribute("auctionBean", auctionBean);
				response.sendRedirect("viewauction.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				List<FileItem> items = new ServletFileUpload(
					    new DiskFileItemFactory()).parseRequest(request);
				String string1 = new String(items.get(0).get());
				if (string1.equals(AUCTION)) {
					//TODO: check correct fields are here
					String action = new String(items.get(0).get());
					
					UserDAO userBean = (UserDAO) request.getSession().getAttribute("userBean"); // get the bean the user created
					String username = userBean.getUsername();
					
					NewAuctionForm form = setNewAuctionForm(request, items);
					if (NewAuctionFormValidator.validate(form).isEmpty()) {
						AuctionDAO auctionDAO = new AuctionDAO();
						auctionDAO.setAttributes(username, 
												form.getTitle(), 
												form.getCategory(), 
												form.getPicture(), 
												form.getDescription(), 
												form.getPostageDetails(), 
												form.getReservePrice(), 
												form.getBiddingStartPrice(), 
												form.getBiddingIncrement(), 
												form.getEndTime());
						
						try {
							auctionDAO.doInsert();
							
							Auction auction = new Auction();
							auction.setAuctionID(auctionDAO.getAuctionID());
							auction.setClosingTime(Integer.parseInt(new String(items.get(9).get())));
							
							AuctionManager.shutDownAuction(auction);
							
							response.sendRedirect("auction.jsp");
						} catch (Exception e) {
							System.out.println("Failed to add new auction");
							response.sendRedirect("auction.jsp");
						}
					} else {
						request.getSession().setAttribute("newAuctionForm", form);
						response.sendRedirect("new-auction.jsp");
					}
				}
			} catch (Exception e) {
				System.out.println("Failed to receive form input");
				response.sendRedirect("new-auction.jsp");
			}
		}
	}

	private NewAuctionForm setNewAuctionForm(HttpServletRequest request, List<FileItem> items) {
		NewAuctionForm form = (NewAuctionForm) request.getSession().getAttribute("newAuctionForm");
		
		if (form == null) {
			form = new NewAuctionForm();
		}
		
		form.setTitle(new String(items.get(1).get()));
		form.setCategory(new String(items.get(2).get()));
		form.setPicture(new Base64().encodeToString(items.get(3).get()));
		form.setDescription(new String(items.get(4).get()));
		form.setPostageDetails(new String(items.get(5).get()));
		form.setPostageDetails(new String(items.get(5).get()));
		form.setReservePrice(Float.parseFloat(new String(items.get(6).get())));
		form.setBiddingStartPrice(Float.parseFloat(new String(items.get(7).get())));
		form.setBiddingIncrement(Float.parseFloat(new String(items.get(8).get())));
		form.setEndTime(Integer.parseInt(new String(items.get(9).get())));
		return form;
	}

	private RegistrationForm setRegistrationForm(HttpServletRequest request) {
		RegistrationForm form = (RegistrationForm) request.getSession().getAttribute(FORM);
		
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
		form.setIsAdmin(request.getParameter("admin").equals("yes"));
		return form;
	}
	
	private List<Auction> searchForAuctions(String title, String category) {
		AuctionDAO auctionDAO = new AuctionDAO();
		
		return auctionDAO.selectWith(title, category);
	}
}

