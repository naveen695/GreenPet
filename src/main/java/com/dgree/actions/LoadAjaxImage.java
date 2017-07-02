package com.dgree.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dgree.model.Image;
import com.dgree.userDAO.MapDao;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class LoadAjaxImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadAjaxImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		 MongoDatabase mongoDatabase = (MongoDatabase)request.getServletContext().getAttribute("MongoDatabase");
		
		 String[] ids = request.getPathInfo().substring(1).split("/");
		
			MapDao mapDao=new MapDao();
			Image loadImage = mapDao.loadImage(mongoClient,ids[0]);
			byte[] fileByte = loadImage.getFileByte();
			response.setContentType(loadImage.getContentType());
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(fileByte);            
			outputStream.flush();
			outputStream.close();
			return;
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
