package com.dgree.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dgree.model.Image;
import com.dgree.userDAO.MapDao;
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
		String html="<div class='jumbotron'> ";
		String htmllast="</div>";
		String id = request.getParameter("id");
		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		Image loadImage = new MapDao().loadImage(mongoClient, id);
		request.setAttribute("images",loadImage);
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	    String image="<img src=\"Image/"+loadImage.getName()+"\" style=\"width : 200px\" >";
	     

	    response.getWriter().write(html.concat(image).concat(htmllast));
	}

}















