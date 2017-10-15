package com.dgree.actions;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.model.SignUpResponce;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.service.UserDetails;
import com.dgree.service.UserSignUp;
import com.dgree.userDAO.Util;
import com.mongodb.client.MongoDatabase;

public class SignUpServlet extends HttpServlet {

	private static Logger logger = LogManager.getLogger();
	
	private static final long serialVersionUID = 1L;
       
    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home").forward(request, response);
	}

	@SuppressWarnings("unchecked")
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
		logger.info("**** Inside SignUpServlet.Post() ****");
		UserBean us=new UserBean();
		String hash = Util.prepareRandomString(30);
	    	
		SignUpResponce sresponce=new SignUpResponce();
		String email = request.getParameter("inputEmail");
        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String password = request.getParameter("inputPassword");
        String password1 = request.getParameter("inputPassword1");
        String mn = request.getParameter("mobilenumber");
        
        us.setEmail(email);
        us.setUserFirstName(firstName);
        us.setUserLastName(lastName);
        us.setPassword(password);
        us.setConformpPssword(password1);
        us.setMobilenumber(mn);
        us.setHash(hash);
        ServletContext servletContext = request.getServletContext();
        MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
       
        UserSignUp userSignUp=new UserSignUp();
        userSignUp.setProp((Map<String, String>) getServletContext().getAttribute("prop"));
        ValidateUser validateUser = userSignUp.validateUser(mongoDatabase,us);
        if (validateUser.isNewUser() == true) {
        	us.setUserid(validateUser.getUserid());
        	StringBuffer requestURL = request.getRequestURL();
        	userSignUp.sendMail(us,requestURL);
        	sresponce.setStatuscode("0");
        	sresponce.setStatusMessage("Registation Link Was Sent To Your Mail Successfully. Please Verify Your Email");
        	logger.info("**** Mail Send succesfully to the user :"+us.getEmail());
        }else if(validateUser.isNewUser()==false){
        	sresponce.setStatuscode("1");
        	sresponce.setStatusMessage("sorry ! .... Your user id already register in our Application. please SignIn.");
        	logger.info("**** sorry ! .... this user id already register in our Application. please SignIn.");
		}
        request.setAttribute("signupresponce", sresponce);
        if("GreenPet".equals(stringurl) || "SignUpServlet".equals(stringurl)){
        	request.getRequestDispatcher("/home").include(request, response);
        return;
        }
        getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
        return;
	}

}
