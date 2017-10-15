package com.dgree.actions;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.service.GoesLocationLatLong;
import com.dgree.service.PetDetailsService;
import com.dgree.service.PetDetailsServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class UpdatePetDetails
 */
public class UpdatePetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger logger= LogManager.getLogger();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePetDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(" ->inside updating pet details .");
		ServletContext servletContext = request.getServletContext();
		MongoClient mongoClient = (MongoClient)servletContext.getAttribute("mongoClient");
	    MongoDatabase mongoDatabase = (MongoDatabase)servletContext.getAttribute("MongoDatabase");

		String stringurl = null ;
		String url = request.getHeader("Referer");
		String[] split = url.split("/");
		for (int i = 0; i <= split.length; i++) {
			if(i==split.length){
				int j=i;
				stringurl = split[--j];
			}
		}
		PetDetails petDetails=new PetDetails();
		petDetails.setId(request.getParameter("petId1"));
		
		petDetails.setPetname(request.getParameter("petname"));
		petDetails.setPetDesc(request.getParameter("petdesc"));
		petDetails.setAddress1(request.getParameter("address1"));
		petDetails.setAddress2(request.getParameter("address2"));
		petDetails.setCounty(request.getParameter("county"));
		petDetails.setCity(request.getParameter("city"));
		petDetails.setCountry(request.getParameter("country"));
		petDetails.setZip(request.getParameter("zip"));
		GoesLocationLatLong goesLocationLatLong= new GoesLocationLatLong();
		goesLocationLatLong.setProp((Map<String, String>) getServletContext().getAttribute("prop"));
		PetDetails petDetails2 = goesLocationLatLong.findLatitudeLongitude(petDetails);
		
		String latitude = petDetails2.getLatitude();
		String longiute = petDetails2.getLongittude();
		if (StringUtils.isEmpty(longiute) || StringUtils.isEmpty(latitude)){
		       logger.info(" - > faild to find longitude/lattitude for this address "); 

				SignUpResponce sresponce=new SignUpResponce();
		    	sresponce.setStatuscode("0");
			  	sresponce.setStatusMessage("Not updated sussesfully ! Please provide valid address."); 
			  	request.setAttribute("signupresponce", sresponce);
			if("UpdatePetDetails".equals(stringurl)){
	        	request.getRequestDispatcher("/home").include(request, response);
	        	return;
	        }
	        	getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	        	return;
		}else{
			logger.info(" - > successlly updated petDetails . "); 

		 	PetDetailsService petDeailsService =new PetDetailsServiceImpl();
        	petDeailsService.updatePetDeails(petDetails2,mongoDatabase);
        	
				SignUpResponce sresponce=new SignUpResponce();
				sresponce.setStatuscode("0");
				sresponce.setStatusMessage("Updated Sussesfully !"); 
				request.setAttribute("signupresponce", sresponce);
				if("GreenPet".equals(stringurl) || "UpdatePetDetails".equals(stringurl)){
					request.getRequestDispatcher("/home").include(request, response);
		        return;
		        }
		        getServletContext().getRequestDispatcher("/HomeServlet").include(request, response);
		        return;
		}
		
	}

}
