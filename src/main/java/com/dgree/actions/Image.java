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

 
public class Image extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public Image() {
        super();
    }

	 
	protected void doGet(HttpServletRequest request1, HttpServletResponse response) throws ServletException, IOException {
		
		String[] ids = request1.getPathInfo().substring(1).split("/");
		
		if (ids.length==1) {
		ArrayList<PetDetails> attribute = (ArrayList<PetDetails>)request1.getSession().getAttribute("petDeails");
		if (attribute != null) {
		
		for (Iterator iterator = attribute.iterator(); iterator.hasNext();) {
			PetDetails petDetails = (PetDetails) iterator.next();
			if ((petDetails != null && petDetails.getPetname().equals(ids[0]))|| (petDetails.getImage().getName() != null && petDetails.getImage().getName().equalsIgnoreCase(ids[0]))) {
				byte[] fileByte = petDetails.getImage().getFileByte();
				response.setContentType("image/jpeg");
				ServletOutputStream outputStream = response.getOutputStream();
				if (fileByte != null) 
					outputStream.write(fileByte);            
				outputStream.flush();
				outputStream.close();
				return;
			}
		}
		}
		}
		 
		 
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
        byte[] imgData = null;
		response.getOutputStream().write(imgData);            
		doGet(request, response);
	}

}
