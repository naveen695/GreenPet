package com.dgree.actions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import com.dgree.model.Image;
import com.dgree.model.LoginUserDetails;
import com.dgree.model.PetDetails;
import com.dgree.userDAO.UploadMultipleImagesDAO;
import com.mongodb.MongoClient;

public class LoadMultipleImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger logger= LogManager.getLogger();

    public LoadMultipleImages() {
        super();
    }

 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info(" LoadMultipleImages post method .");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		LoginUserDetails loginUserDetails = (LoginUserDetails)request.getSession().getAttribute("loginUserDetails");
		
		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		List<String> loadMultipleImage = new UploadMultipleImagesDAO().loadMultipleImage(id,mongoClient);
			if (!loadMultipleImage.isEmpty()) {
				StringBuilder outputResponce=new StringBuilder();
				StringBuilder html= new StringBuilder("<div class='jumbotron' style=\"padding-top: 10px;background-color: honeydew;margin-bottom: 0px;\">");
				String pageIndex = request.getParameter("pageIndex");
				Integer page=1;
				if (StringUtils.isNotBlank(pageIndex)) {
					int pageNum = 0;
					try{
						pageNum = Integer.parseInt(pageIndex);
					}catch(Exception e){
						logger.info(" Number Format Exce "+e);
					}
					if ("next".equals(type)) {
						if (loadMultipleImage.size()==pageNum) {
							page=1;
						}else{
							page = ++pageNum;
						}
					}else if("back".equals(type)){
						if (loadMultipleImage.size()==1) {
							page = loadMultipleImage.size();
						}else{
							page = --pageNum;
						}
					}	
				}
				String loadingImageID = null;
				int pageCount;
					if (page==0) {
						loadingImageID = loadMultipleImage.get(loadMultipleImage.size()-1);
						pageCount=loadMultipleImage.size();
					} else if(page<0){
						loadingImageID = loadMultipleImage.get(0);
						pageCount=1;
					}else{
						loadingImageID = loadMultipleImage.get(page-1);
						pageCount=page;
					}
					outputResponce.append(""
					+ "<a onclick=\"zoom1();\" style=\"padding: 0px;\"  data-toggle=\"modal\" data-target=\"#myModal\"><img id=\"image1\" alt=\" loading ... \" src=\"LoadAjaxImage/"+loadingImageID+"\" style=\"height: 100%;width: 60%;\"></a>"
					+ "<br/>"
					+ "<a href=\"#\" class=\"glyphicon glyphicon-triangle-left\"  onclick=\"loadajaximages(\'"+id+"\','back')\" ></a>"
					+ ""+pageCount+"/"+loadMultipleImage.size()+"<input type=\"hidden\" id=\"pageIndex\" value=\""+pageCount+"\"> "
					+ "<a href=\"#\" class=\"glyphicon glyphicon-triangle-right\" onclick=\"loadajaximages(\'"+id+"\','next')\"></a> "
					+ "<br/>"
					+ "</form>");
			
					Image image = new Image() ;
					Integer likes = 0;
					Integer dislikes=0;
					boolean liked = false ;
					Integer imgLikes =0;
					String str = "";
					if (loginUserDetails!=null) {
						PetDetails loadlikesandDislikes = new UploadMultipleImagesDAO().loadlikesandDislikes(mongoClient, id,loginUserDetails.getEmail());
						List<Image> imageList = loadlikesandDislikes.getImageList();
						for (Iterator<Image> iterator = imageList.iterator(); iterator.hasNext();) {
							image = iterator.next();
							likes += image.getLikes();
							if (StringUtils.isNotBlank(loadingImageID) && loadingImageID.equals(image.getIds())) {
								liked = image.isLiked();
								imgLikes = image.getLikes();
							}
						}
						
					}
					if (liked) {
						str = ""
								+ "<div class=\"row\"  style=\"margin-right: -0px;margin-left: -0px;\"> "
								+ "<div class=\"col-sm-6\" > "
								+ "<a href=\"#\" class=\"dislike\" id=\"dislikes\"  style=\"padding-bottom: 0px;\"  onclick=\"send('dislikes', \'"+id+"\',\'"+loadingImageID+"\', \'"+imgLikes+"\',\'"+likes+"\')\">"
								+ "<i class=\"fa fa-thumbs-o-up\"></i>DisLike  <input class=\"qty1\" name=\"qty1\" readonly=\"readonly\" type=\"text\" value=\""+likes+"\"/> "
								+ "</a>"
								+ "</div>"
								+ "<div class=\"col-sm-6\" class=\"dislike\"  >"
								+ "<a onclick=\"myFunction()\"  class=\"like\" style=\"color: #2962ff;font-size: 12px;font-weight: normal;cursor: pointer;text-align: center;text-transform: uppercase;\">"
								+ "<input type=\"hidden\" id=\"petID\" name=\"petID\" value=\'"+id+"\'>"
								+"<input type=\"hidden\" id=\"imageID\" name=\"imageID\" value=\'"+loadingImageID+"\'>"
								+ "See Comments </a>"
								+ " </div>"
								
								+ "</div>"
								;
					}else{
						str = ""
								+ " <div class=\"row\" style=\"margin-right: -0px;margin-left: -0px;\">"
								+ "<div class=\"col-sm-6\"> "
								+ "<a href=\"#\" class=\"like\" id=\"likes\"  style=\"padding-bottom: 0px;\"   onclick=\"send('likes', \'"+id+"\',\'"+loadingImageID+"\',\'"+imgLikes+"\',\'"+likes+"\')\">"
								+ "<i class=\"fa fa-thumbs-o-up\"></i>Like  <input class=\"qty1\" name=\"qty1\" readonly=\"readonly\" type=\"text\" value=\""+likes+"\"/> "
								+ "</a>"
								+ "</div>"
								+ "<div class=\"col-sm-6\" >"
								+ "<a onclick=\"myFunction()\" class=\"like\" style=\"color: #2962ff;font-size: 12px;font-weight: normal;cursor: pointer;text-align: center;text-transform: uppercase;\">"
								+ "<input type=\"hidden\" id=\"petID\" name=\"petID\" value=\'"+id+"\'>"
								+"<input type=\"hidden\" id=\"imageID\" name=\"imageID\" value=\'"+loadingImageID+"\'>"
								+ "See Comments </a>"
								+ "</div>"
								
								+ "</div>"
								;
					}
			  	response.setContentType("application/json;charset=utf-8");
		        JSONObject json = new JSONObject();
		        json.put("status",liked);
		        json.put("petID", id);
		        json.put("imageID",loadingImageID);
		        json.put("likes",likes);
		        json.put("content",str);
		        json.put("data",html.append(outputResponce.toString()).append("</div>").toString());
		        response.getWriter().write(json.toString());
			}
	}

}















