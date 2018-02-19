package com.dgree.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.model.Image;
import com.dgree.userDAO.PetDetailsDaoImpl;
import com.mongodb.MongoClient;

public class LoadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public LoadImage() {
        super();
    }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getPathInfo().substring(1).split("/");
		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
			PetDetailsDaoImpl load=new PetDetailsDaoImpl();
			Image loadImage = load.loadImage(mongoClient, ids[0]);
				byte[] fileByte = loadImage.getFileByte();
				response.setContentType("image/jpeg");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(fileByte);            
				outputStream.flush();
				outputStream.close();
				return;
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
        byte[] imgData = null;
		response.getOutputStream().write(imgData);            
		doGet(request, response);
	}

}
