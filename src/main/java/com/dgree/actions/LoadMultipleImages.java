package com.dgree.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.model.Image;
import com.dgree.userDAO.MapDao;
import com.dgree.userDAO.UploadMultipleImagesDAO;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class LoadMultipleImages
 */
public class LoadMultipleImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadMultipleImages() {
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
		StringBuilder outputResponce=new StringBuilder();
		StringBuilder html= new StringBuilder("<div class='jumbotron'>");
		String id = request.getParameter("id");
		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		List<String> loadMultipleImage = new UploadMultipleImagesDAO().loadMultipleImage(id,mongoClient);
		
		for (String idString : loadMultipleImage) {
			outputResponce.append("<img src=\"LoadAjaxImage/"+idString+"\" style=\"width : 200px\" ><br/>");
		}
		response.getWriter().write(html.append(outputResponce.toString()).append("</div>").toString());
	}

}















