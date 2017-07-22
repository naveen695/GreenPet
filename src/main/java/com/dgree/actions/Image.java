package com.dgree.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.dgree.model.PetDetails;

/**
 * Servlet implementation class Image
 */
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Image() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request1, HttpServletResponse response) throws ServletException, IOException {
		
		String[] ids = request1.getPathInfo().substring(1).split("/");
		
		if (ids.length==1) {
		ArrayList<PetDetails> attribute = (ArrayList<PetDetails>)request1.getSession().getAttribute("petDeails");
		if (attribute != null) {
		
		for (Iterator iterator = attribute.iterator(); iterator.hasNext();) {
			PetDetails petDetails = (PetDetails) iterator.next();
			if ((petDetails != null && petDetails.getPetname().equals(ids[0]))|| petDetails.getImage().getName().equalsIgnoreCase(ids[0])) {
				byte[] fileByte = petDetails.getImage().getFileByte();
				response.setContentType("image/jpeg");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(fileByte);            
				outputStream.flush();
				outputStream.close();
				return;
			}
		}
		}
		}
		 
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		 response.setContentType("image/jpeg");
        byte[] imgData = null;
		response.getOutputStream().write(imgData);            

		doGet(request, response);
	}

}
