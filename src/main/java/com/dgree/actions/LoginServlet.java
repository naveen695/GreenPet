
package com.dgree.actions;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.service.PetDetailsService;
import com.dgree.service.PetDetailsServiceImpl;
import com.dgree.service.UserDetails;
import com.dgree.service.UserLogin;
import com.dgree.service.UserSignUp;
import com.dgree.userDAO.Util;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home").forward(request, response);
	}
	
	

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
	    	
	LoginUserDetails loginUserDetails=new LoginUserDetails();
	
		
		String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        UserBean us=new UserBean();
		us.setEmail(email);
	    us.setPassword(pwd);
	     
	    ServletContext servletContext = request.getServletContext();
	    MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");

	    SignUpResponce sresponce=new SignUpResponce();
	    UserDetails userSignUp=new UserLogin();
	    ValidateUser validateUser = userSignUp.validateUser(mongoDatabase,us);
	   
	    logger.info(validateUser.toString());
	  if (validateUser.isLoginValid()==true && validateUser.getLoginStauts().equals("active")) {
		   	loginUserDetails = validateUser.getLoginUserDetails();
		   	loginUserDetails.setLogin(true);
		  	loginUserDetails.setMessage("succes");
		  	sresponce.setStatuscode("0");
		  	sresponce.setStatusMessage("login susses !"); 
		  	
		  	
		  	
		  	
		  	// getting data from db
      	    MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
		  	
		  	loadUserPetInfo(loginUserDetails,mongoClient);
		  	
		  	
		  	
		  	HttpSession session = request.getSession();
			session.setAttribute("loginUserDetails", loginUserDetails);
			  

	  }else if(validateUser.isLoginValid()==true && validateUser.getLoginStauts().equals("not_active")){
		  loginUserDetails.setLogin(false);
		  loginUserDetails.setMessage("not_activate");
		  sresponce.setStatuscode("1");
		  sresponce.setStatusMessage("validate your mail !");

	  }else if(validateUser.isLoginValid()==false && validateUser.getLoginStauts().equals("not_active")){
		  loginUserDetails.setLogin(false);
		  loginUserDetails.setMessage("not_match");
		  sresponce.setStatuscode("1");
		  sresponce.setStatusMessage("username/pasword not match or no user found .please sign up !");
  }
    
	  	request.setAttribute("signupresponce", sresponce);
	        if("GreenPet".equals(stringurl) || "LoginServlet".equals(stringurl) ||"LogOutServlet".equals(stringurl) ){
	        	request.getRequestDispatcher("/home").include(request, response);
	        return;
	        }
	        getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	        return;

	}



	private void loadUserPetInfo(LoginUserDetails loginUserDetails, MongoClient mongoClient) {
		PetDetailsService detailsService=new PetDetailsServiceImpl();
		List<PetDetails> loadPetDeails = detailsService.loadPetDeails(loginUserDetails,mongoClient);
		
		
	}

}





