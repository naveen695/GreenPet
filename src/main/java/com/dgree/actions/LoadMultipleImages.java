package com.dgree.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

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
		String type = request.getParameter("type");
		String id = request.getParameter("id");

		MongoClient mongoClient = (MongoClient)request.getServletContext().getAttribute("mongoClient");
		List<String> loadMultipleImage = new UploadMultipleImagesDAO().loadMultipleImage(id,mongoClient);
	if (!loadMultipleImage.isEmpty()) {
	
		StringBuilder outputResponce=new StringBuilder();
		StringBuilder html= new StringBuilder("<div class='jumbotron'>");
		
		String pageIndex = request.getParameter("pageIndex");
		Integer page=1;
		 
		if (StringUtils.isNotBlank(pageIndex)) {
			int pageNum = 0;
			try{
				pageNum = Integer.parseInt(pageIndex);
			}catch(Exception e){
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
			  loadingImageID = loadMultipleImage.get(page);
			  pageCount=page+1;
		} else if(page<0){
			  loadingImageID = loadMultipleImage.get(0);
			  pageCount=1;
		}else{
		  loadingImageID = loadMultipleImage.get(page-1);
		  pageCount=page;

		}
		
		
			
			outputResponce.append("<a onclick=\"zoom1();\"   data-toggle=\"modal\" data-target=\"#myModal\"><img id=\"image1\" src=\"LoadAjaxImage/"+loadingImageID+"\" style=\"width: 250px;height: 340px;\"></a><br/></br>"
					+ "<br>"+pageCount+"/"+loadMultipleImage.size()+"<input type=\"hidden\" id=\"pageIndex\" value=\""+page+"\"> </br>"
							+ "<input type=\"submit\" value=\"back\" onclick=\"loadajaximages(\'"+id+"\','back')\"> "
									+ "<input type=\"submit\" value=\"next\" onclick=\"loadajaximages(\'"+id+"\','next')\"> </form>");
	
		response.getWriter().write(html.append(outputResponce.toString()).append("</div>").toString());
	}
	}

}















