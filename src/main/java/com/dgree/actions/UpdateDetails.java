package com.dgree.actions;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.service.UserDetails;
import com.dgree.service.UserSignUp;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class UpdateDetails
 */
public class UpdateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringurl = null ;
		String url = request.getHeader("Referer");
		String[] split = url.split("/");
		for (int i = 0; i <= split.length; i++) {
			if(i==split.length){
				int j=i;
				stringurl = split[--j];
			}
		}
		String updateName = request.getParameter("updateName");
        String updateLastName = request.getParameter("updateLastName");
        String emailUpdate = request.getParameter("emailUpdate");
        String updateMobileNumber = request.getParameter("updateMobileNumber");

		
        logger.info("**** Inside updateservlet.Post() ****");
		UserBean us=new UserBean();
			us.setEmail(emailUpdate);
	        us.setUserFirstName(updateName);
	        us.setUserLastName(updateLastName);
	        us.setMobilenumber(updateMobileNumber);
	        
	        ServletContext servletContext = request.getServletContext();
	        MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
	       
	        UserDetails userSignUp=new UserSignUp();
	        LoginUserDetails userDetails = userSignUp.updateUserDetails(mongoDatabase,us);
	        if(userDetails != null){
	        	HttpSession session = request.getSession();
	        	Object attribute = session.getAttribute("loginUserDetails");
	        		LoginUserDetails loginUserDetails = (LoginUserDetails)attribute;
	        		loginUserDetails.setUserFirstName(userDetails.getUserFirstName());
	        		loginUserDetails.setUserLastName(userDetails.getUserLastName());
	        		loginUserDetails.setMobilenumber(userDetails.getMobilenumber());
	        		session.setAttribute("loginUserDetails",loginUserDetails);
	        SignUpResponce sresponce=new SignUpResponce();
	    	sresponce.setStatuscode("0");
		  	sresponce.setStatusMessage("updated sussesfully !"); 
	        request.setAttribute("signupresponce", sresponce);
	        if("GreenPet".equals(stringurl) ||"UpdateDetails".equals(stringurl) ){
	        	request.getRequestDispatcher("/about").include(request, response);
	        return;
	        }
	        getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	        return;
}
	}
}
