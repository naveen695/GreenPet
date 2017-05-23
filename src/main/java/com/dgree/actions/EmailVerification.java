package com.dgree.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.SignUpResponce;
import com.dgree.model.UserBean;
import com.dgree.service.SignUp;
import com.dgree.service.UserSignUp;
import com.dgree.userDAO.User;
import com.dgree.userDAO.UserDao;
import com.dgree.userDAO.Util;
import com.mongodb.client.MongoDatabase;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class EmailVerification
 */
public class EmailVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
	   
    public EmailVerification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SignUpResponce sresponce=new SignUpResponce();
		
		try{
		// get user Id and email verification code Hash code  
				Long userId = Long.parseLong(request.getParameter("userId"));
				String hash = request.getParameter("hash");
				String scope = request.getParameter("scope");
				UserBean us=new UserBean();
					
				us.setUserid(userId);
				us.setHash(hash);
				SignUp user=new UserSignUp();
				ServletContext servletContext = request.getServletContext();
			    MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
				boolean validateUserMail = user.validateUserMail(mongoDatabase, us);
				
				if (validateUserMail == true ) {
					sresponce.setStatuscode("0");
					sresponce.setStatusMessage("Congratulations ! you have successfully verified your E-Mail address .");
					logger.info("validated succfully.");
				}else{
					sresponce.setStatuscode("1");
					sresponce.setStatusMessage("Sorry ! you have already  verified your E-Mail address.");
					logger.info("already validated succfully.");
				}
		}catch (Exception e) {
			logger.info(e.toString());
		}
		request.setAttribute("signupresponce", sresponce);
		
		getServletContext().getRequestDispatcher("/pages/EmailVerification.jsp").forward(request, response);
		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
