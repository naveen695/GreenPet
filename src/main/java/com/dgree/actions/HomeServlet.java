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

import org.apache.commons.lang3.StringUtils;

import com.dgree.dbUtil.DBConnectionImpl;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.service.PetDetailsService;
import com.dgree.service.PetDetailsServiceImpl;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DBConnectionImpl.class.getName());

	
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		logger.info(" Inside home servlet .");
		PetDetails petDetails=new PetDetails();
		
		String stringurl = null ;
		String url = request.getHeader("Referer");
		if (StringUtils.isNotBlank(url)) {
		String[] split = url.split("/");
		for (int i = 0; i <= split.length; i++) {
			if(i==split.length){
				int j=i;
				stringurl = split[--j];
			}
		}
		}
		HttpSession session = request.getSession(false);
		if (session!=null) {
		Object attribute = session.getAttribute("loginUserDetails");
		LoginUserDetails loginUserDetails=(LoginUserDetails)attribute;
			
		if (loginUserDetails != null && loginUserDetails.isLogin()) {
			ServletContext servletContext = request.getServletContext();
			MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
			PetDetailsService detailsService=new PetDetailsServiceImpl();
			List<PetDetails> loadPetDeails = detailsService.loadPetDeails(loginUserDetails,mongoClient);
			session.setAttribute("petDeails",loadPetDeails);	
		}else{
			SignUpResponce responce1=new SignUpResponce();
			responce1.setStatuscode("0");
			responce1.setStatusMessage("Please login !!!!!.");
			request.setAttribute("msg",responce1);	
		}
	}else{
			SignUpResponce responce1=new SignUpResponce();
			responce1.setStatuscode("0");
			responce1.setStatusMessage("Please Login !!!!!.");
			request.setAttribute("msg",responce1);	
			
		}
		request.getRequestDispatcher("home").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doGet(request, response);}

}
