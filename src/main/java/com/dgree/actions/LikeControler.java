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
			str = "<div class=\"row\"  style=\"margin-right: -0px;margin-left: -0px;\"> "
			+ "<div class=\"col-sm-6\" > "
			+ "<a href=\"#\" class=\"dislike\" id=\"dislikes\"  style=\"padding-bottom: 0px;\"  onclick=\"send('dislikes', \'"+id+"\',\'"+imageId+"\', \'"+likes+"\',\'"+petlikes+"\')\">"
			+ "<i class=\"fa fa-thumbs-down\">  </i>  <input class=\"qty1\" name=\"qty1\" readonly=\"readonly\" type=\"text\" value=\""+petlikes+"\"/> "
			+ "</a>"
			+ "</div>"
			+ "<div class=\"col-sm-6\" class=\"dislike\"  >"
			+ "<a onclick=\"myFunction()\"  class=\"like\" style=\"color: #2962ff;font-size: 12px;font-weight: normal;cursor: pointer;text-align: center;text-transform: uppercase;\">"
			+ "<input type=\"hidden\" id=\"petID\" name=\"petID\" value=\'"+id+"\'>"
			+"<input type=\"hidden\" id=\"imageID\" name=\"imageID\" value=\'"+imageId+"\'>"
			+ "See Comments </a>"
			+ " </div>"
			
			+ "</div>"
			;
			if (loginUserDetails!=null) {	
				dao.insertLikesDislikes(mongoClient,id,imageId,loginUserDetails.getEmail(),likes,"likes");
			}
		}else if("dislikes".equalsIgnoreCase(type.trim())){
			likes--;
			petlikes--;
			str = " <div class=\"row\" style=\"margin-right: -0px;margin-left: -0px;\">"
			+ "<div class=\"col-sm-6\"> "
			+ "<a href=\"#\" class=\"like\" id=\"likes\"  style=\"padding-bottom: 0px;\"   onclick=\"send('likes', \'"+id+"\',\'"+imageId+"\',\'"+likes+"\',\'"+petlikes+"\')\">"
			+ "<i class=\"fa fa-thumbs-o-up\"></i>  <input class=\"qty1\" name=\"qty1\" readonly=\"readonly\" type=\"text\" value=\""+petlikes+"\"/> "
			+ "</a>"
			+ "</div>"
			+ "<div class=\"col-sm-6\" >"
			+ "<a onclick=\"myFunction()\" class=\"like\" style=\"color: #2962ff;font-size: 12px;font-weight: normal;cursor: pointer;text-align: center;text-transform: uppercase;\">"
			+ "<input type=\"hidden\" id=\"petID\" name=\"petID\" value=\'"+id+"\'>"
			+"<input type=\"hidden\" id=\"imageID\" name=\"imageID\" value=\'"+imageId+"\'>"
			+ "See Comments </a>"
			+ "</div>"
			
			+ "</div>";
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
