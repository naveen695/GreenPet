package com.dgree.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.dgree.model.PetDetails;
import com.dgree.model.SignUpResponce;
import com.dgree.service.GoesLocationLatLong;

/**
 * Servlet implementation class UplodePetDetails
 */
public class UplodePetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UplodePetDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		PetDetails petDetails=new PetDetails();
		petDetails.setPetname(request.getParameter("petname1"));
		petDetails.setAddress1(request.getParameter("address11"));
		petDetails.setAddress2(request.getParameter("address21"));
		petDetails.setCity(request.getParameter("county1"));
		petDetails.setCity(request.getParameter("city1"));
		petDetails.setCountry(request.getParameter("country1"));
		petDetails.setZip(request.getParameter("zip1"));
		GoesLocationLatLong goesLocationLatLong= new GoesLocationLatLong();
		goesLocationLatLong.findLatitudeLongitude(petDetails);
		String latitude = petDetails.getLatitude();
		String longiute = petDetails.getLongiute();
		if (StringUtils.isEmpty(latitude) || StringUtils.isEmpty(longiute)){
			 SignUpResponce sresponce=new SignUpResponce();
		    	sresponce.setStatuscode("0");
			  	sresponce.setStatusMessage("not updated sussesfully !"); 
			  	request.setAttribute("signupresponce", sresponce);
				
	        if("UploadPetDetails".equals(stringurl)){
	        	request.getRequestDispatcher("/home").include(request, response);
	        return;
	        }
	        getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
	        return;
		}
		 SignUpResponce sresponce=new SignUpResponce();
	    	sresponce.setStatuscode("0");
		  	sresponce.setStatusMessage("updated sussesfully !"); 
		  	request.setAttribute("signupresponce", sresponce);
			
		if("GreenPet".equals(stringurl) || "UpdatePetDetails".equals(stringurl)){
			request.getRequestDispatcher("/home").include(request, response);
        return;
        }
        getServletContext().getRequestDispatcher("/".concat(stringurl)).include(request, response);
        return;
	}

}
