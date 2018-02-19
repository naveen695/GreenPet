package com.dgree.actions;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.userDAO.PetDetailsDao;
import com.dgree.userDAO.PetDetailsDaoImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

 
public class PetIdApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PetIdApprove() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/AdminControler").forward(request, response);
	}


	public static Logger logger= (Logger) LogManager.getLogger();	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("petidApprove");
		logger.info(" Inside approve servlet .");
		HttpSession session = request.getSession(false);
		if (session!=null) {
			Object attribute = session.getAttribute("loginUserDetails");
			LoginUserDetails loginUserDetails=(LoginUserDetails)attribute;
		if (loginUserDetails != null && loginUserDetails.isLogin()) {
		
			ServletContext servletContext = request.getServletContext();
			MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
			MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
			PetDetailsDaoImpl detailsDao=new PetDetailsDaoImpl();
			PetDetails petDetails = new PetDetails();
			petDetails.setId(parameter);
			detailsDao.approvePetDeails(petDetails, mongoDatabase);;
		}		
		
		
		}
	  	request.getRequestDispatcher("/AdminControler").forward(request, response);

		}


}
