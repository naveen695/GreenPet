package com.dgree.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.userDAO.UploadMultipleImagesDAO;
import com.mongodb.MongoClient;

/**
 * Servlet implementation class LikeControler
 */
public class LikeControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String id = request.getParameter("id");

		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String imageId = request.getParameter("loadingImageID");
		String parameter = request.getParameter("imagelikes");

		String pt = request.getParameter("petlikes");
		int petlikes= Integer.parseInt(pt);

		int likes= Integer.parseInt(parameter);
		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		LoginUserDetails loginUserDetails = (LoginUserDetails)request.getSession().getAttribute("loginUserDetails");
		
		String str = "";
		UploadMultipleImagesDAO dao=new UploadMultipleImagesDAO();
		if("likes".equalsIgnoreCase(type.trim())){
			likes++;
			petlikes++;
			str = "<a href=\"#\" class=\"dislike\" id=\"dislikes\"   onclick=\"send('dislikes', \'"+id+"\',\'"+imageId+"\', \'"+likes+"\',\'"+likes+"\')\">"
					+ "<i class=\"fa fa-thumbs-o-up\"></i>DisLike  <input class=\"qty1\" name=\"qty1\" readonly=\"readonly\" type=\"text\" value=\""+petlikes+"\"/> "
					+ "</a>";
			if (loginUserDetails!=null) {	
				dao.insertLikesDislikes(mongoClient,id,imageId,loginUserDetails.getEmail(),likes,"likes");
			}
		}else if("dislikes".equalsIgnoreCase(type.trim())){
			likes--;
			petlikes--;
			str = "<a href=\"#\" class=\"like\" id=\"likes\"   onclick=\"send('likes', \'"+id+"\',\'"+imageId+"\',\'"+likes+"\',\'"+likes+"\')\">"
					+ "<i class=\"fa fa-thumbs-o-up\"></i>Like  <input class=\"qty1\" name=\"qty1\" readonly=\"readonly\" type=\"text\" value=\""+petlikes+"\"/> "
					+ "</a>";
			if (loginUserDetails!=null) {
				dao.insertLikesDislikes(mongoClient,id,imageId,loginUserDetails.getEmail(),likes,"dislikes");
			}
		}
		
		
		response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        
        json.put("petID", id);
        json.put("likes",likes);
        json.put("content",str);
 
        response.getWriter().write(json.toString());

		

	}

}
