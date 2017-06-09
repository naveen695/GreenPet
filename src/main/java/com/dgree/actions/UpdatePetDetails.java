package com.dgree.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.model.PetDetails;

/**
 * Servlet implementation class UpdatePetDetails
 */
public class UpdatePetDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		petDetails.setPetname(request.getParameter("petname"));
		petDetails.setAddress1(request.getParameter("address1"));
		petDetails.setAddress2(request.getParameter("address2"));
        petDetails.setCity(request.getParameter("city"));
		petDetails.setCountry(request.getParameter("country"));
		petDetails.setZip(request.getParameter("zip"));

        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		doGet(request, response);
	}

}
