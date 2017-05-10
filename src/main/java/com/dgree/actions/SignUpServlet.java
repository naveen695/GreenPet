package com.dgree.actions;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.UserBean;
import com.dgree.service.SignUp;
import com.dgree.service.UserSignUp;
import com.mongodb.client.MongoDatabase;

public class SignUpServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());
	
	private static final long serialVersionUID = 1L;
       
    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("**** Inside SignUpServlet.Post() ****");
		UserBean us=new UserBean();
		String email = request.getParameter("inputEmail");
        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String password = request.getParameter("inputPassword");
        String password1 = request.getParameter("inputPassword1");
        us.setEmail(email);
        us.setUserFirstName(firstName);
        us.setUserLastName(lastName);
        us.setPassword(password);
        us.setConformpPssword(password1);
        
        ServletContext servletContext = request.getServletContext();
        MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
        
        SignUp userSignUp=new UserSignUp();
        Boolean validateUser = userSignUp.validateUser(mongoDatabase,us);
        if (validateUser.equals("true")) {

        	
		}else{
		
		userSignUp.sendMail(us);
		logger.info("**** Mail Send succesfully to the user :"+us.getEmail());
		
		}
        
        
       
	}

}
