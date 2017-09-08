package com.dgree.actions;

import java.io.IOException;
import java.util.HashMap;
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
import com.dgree.service.Constants;
import com.dgree.service.MailService;
import com.dgree.userDAO.Util;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class Contact
 */
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger();       
    public Contact() {
        super();
     }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stringurl = null ;
		String url = request.getHeader("Referer");
		String[] split = url.split("/");
		for (int i = 0; i <= split.length; i++) {
			if(i==split.length){
				int j=i;
				stringurl = split[--j];
			}
		}
		logger.info("**** Inside contact.get() ****");
		String hash = Util.prepareRandomString(30);
		String email = request.getParameter("inputEmailID");
        String name = request.getParameter("inputFirstName1");
        String mob = request.getParameter("mobilenumber1");
        String comments = request.getParameter("comments");
        String heading = request.getParameter("heading");
        Map<String, String> details =new HashMap<>();
        details.put(Constants.EMAIL, email);
        details.put(Constants.NAME, name);
        details.put(Constants.MOBILE, mob);
        details.put(Constants.COMMENTS, comments);
        details.put(Constants.HEADING, heading);
           
        MailService ms=new MailService();
        
        StringBuffer requestURL = request.getRequestURL();
    	ms.sendMail(details,requestURL);
    	//save data in db
        ServletContext servletContext = request.getServletContext();
        MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
        SignUpResponce sresponce=new SignUpResponce();
        sresponce.setStatuscode("0");
	  	sresponce.setStatusMessage("Mail sent Successfully. Thanks !"); 
	  	request.setAttribute("signupresponce", sresponce);
        request.getRequestDispatcher("contact").forward(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
