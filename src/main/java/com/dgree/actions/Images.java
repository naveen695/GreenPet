package com.dgree.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.model.Image;
import com.dgree.model.PetDetails;

/**
 * Servlet implementation class Images
 */
public class Images extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Images() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String[] ids = request.getPathInfo().substring(1).split("/");
		
		if (ids.length==1) {
		ArrayList<Image> attribute = (ArrayList<Image>)request.getAttribute("images");
		if (attribute != null) {
		
		for (Iterator iterator = attribute.iterator(); iterator.hasNext();) {
			Image image = (Image) iterator.next();
			if ((image != null && image.getName().equalsIgnoreCase(ids[0]))) {
				byte[] fileByte = image.getFileByte();
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
		doGet(request, response);
	}

}
