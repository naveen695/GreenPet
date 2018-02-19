package com.dgree.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.PageListDataModel;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.service.PetDetailsService;
import com.dgree.service.PetDetailsServiceImpl;
import com.dgree.userDAO.PetDetailsDao;
import com.dgree.userDAO.PetDetailsDaoImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class LoadImagesForVerification
 */
public class LoadImagesForVerification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadImagesForVerification() {
        super();
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}	

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession(false);
		if (session!=null) {
			Object attribute = session.getAttribute("loginUserDetails");
			LoginUserDetails loginUserDetails=(LoginUserDetails)attribute;
				if (loginUserDetails != null && loginUserDetails.isLogin()) {
				
					ServletContext servletContext = request.getServletContext();
					MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");
					MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
					PetDetailsDaoImpl detailsDao=new PetDetailsDaoImpl();
			//		Map<String, String> loadImagesForVerification = detailsDao.loadImagesForVerification(loginUserDetails, mongoDatabase);
			//		request.setAttribute("loadImages",loadImagesForVerification);	
				}
		}
        response.getWriter().write("data Image not verified");

 	}

}
