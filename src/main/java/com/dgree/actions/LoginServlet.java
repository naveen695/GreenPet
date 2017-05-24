package com.dgree.actions;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.SignUpResponce;
import com.dgree.model.UserBean;
import com.dgree.model.ValidateUser;
import com.dgree.service.UserDetails;
import com.dgree.service.UserLogin;
import com.dgree.service.UserSignUp;
import com.dgree.userDAO.Util;
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
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");

        UserBean us=new UserBean();
		SignUpResponce sresponce=new SignUpResponce();
		us.setEmail(email);
	    us.setPassword(pwd);
	     
	    ServletContext servletContext = request.getServletContext();
	    MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
	       
	    UserDetails userSignUp=new UserLogin();
	    ValidateUser validateUser = userSignUp.validateUser(mongoDatabase,us);
	    logger.info(validateUser.toString());
	}

}
