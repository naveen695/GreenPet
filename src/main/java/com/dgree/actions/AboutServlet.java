package com.dgree.actions;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class AboutServlet
 */
public class AboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(UploadMultipleImages.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutServlet() {
        super();
        // TODO Auto-generated constructor stub	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("inside about servlet");
		HttpSession session = request.getSession();
		if (session != null) {
		LoginUserDetails loginUserDetails = (LoginUserDetails)session.getAttribute("loginUserDetails");
		if (loginUserDetails !=null) {
				PetDetailsDao detailsDao=new PetDetailsDaoImpl();
				ServletContext servletContext = request.getServletContext();
				MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
				List<PetDetails> loadPetDeailsWithOutImage = detailsDao.loadPetDeailsWithOutImage(loginUserDetails, mongoClient);
				request.setAttribute("petDeails", loadPetDeailsWithOutImage);
		}
		}
	    request.getRequestDispatcher("about").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
